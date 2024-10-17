package com.monitor.service.monitorpkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.monitor.common.exception.NotFoundPullRequestException;
import com.monitor.common.utils.ObjectMapperUtil;
import com.monitor.domain.gitee.PullRequest;
import com.monitor.domain.monitorpkg.converter.IMonitorPkgConverter;
import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;
import com.monitor.service.gitee.IGiteeService;

@Service
public class UpStreamYmlHandler implements IUpStreamYmlHandler {
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UpStreamYmlHandler.class);

    /**
     * gitee service.
     */
    @Autowired
    private IGiteeService giteeService;

    /**
     * monitor pkg converter.
     */
    @Autowired
    private IMonitorPkgConverter converter;

    /**
     * get updated upstream.
     */
    @Override
    public List<AbstractMonitorPkg> getUpdatedUpstream(String text) {
        LOGGER.info("body: {}", text);
        PullRequest pr = getPrFromWebHook(text);
        if (pr == null) {
            return Collections.emptyList();
        }

        String files = giteeService.getFilesFromPr(pr);
        JsonNode node = ObjectMapperUtil.toJsonNode(files);
        if (node == null) {
            return Collections.emptyList();
        }

        List<JsonNode> nodeList = new ArrayList<>();
        for (JsonNode fileNode : node) {
            if (validPath(fileNode)) {
                nodeList.add(fileNode);
            }
        }
        if (nodeList.isEmpty()) {
            LOGGER.info("no updated pkg, owner: {}, repo: {}, id: {}", pr.getOwner(), pr.getRepo(), pr.getId());
            return Collections.emptyList();
        }

        List<AbstractMonitorPkg> pkgList = new ArrayList<>();
        for (JsonNode fileNode : nodeList) {
            String rawFile = "";
            try {
                rawFile = getRawFile(fileNode, pr);
                AbstractMonitorPkg pkg = converter.convertRawYmlFileToMonitorPkg(rawFile);
                if (pkg != null) {
                    pkgList.add(pkg);
                }
            } catch (Exception e) {
                LOGGER.error("fail to get monitor, pr: id: {}, owner: {}, repo: {}, file: {}, cause: {}",
                        pr.getId(), pr.getOwner(), pr.getRepo(), rawFile, e.getMessage());
            }
        }
        return pkgList;
    }

    /**
     * valid path.
     * @param fileNode file node of pull request.
     * @return boolean.
     */
    private boolean validPath(JsonNode fileNode) {
        try {
            String path = fileNode.get("filename").asText();
            String[] pieces = path.split("/");
            Set<String> nameSet = Arrays.stream(pieces).collect(Collectors.toSet());

            if ("sig".equals(pieces[0]) && nameSet.contains("src-openeuler")
                    && (path.endsWith(".yaml") || path.endsWith(".yml"))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.error("fail to get filename, cause: {}", e.getMessage());
            return false;
        }
    }

    /**
     * get raw file.
     * @param fileNode file node.
     * @param pr pull request.
     * @return raw file.
     * @throws Exception Exception.
     */
    private String getRawFile(JsonNode fileNode, PullRequest pr) throws Exception {
        String commitId = fileNode.get("sha").asText();
        String fileName = fileNode.get("filename").asText();
        return giteeService.getRawFile(pr.getOwner(), pr.getRepo(), fileName, commitId);
    }

    /**
     * get pull request from webhook.
     * @param text webhook text.
     * @return pull request.
     */
    private PullRequest getPrFromWebHook(String text) {
        JsonNode node = ObjectMapperUtil.toJsonNode(text);
        if (node == null) {
            throw new NotFoundPullRequestException("blank webhook: " + text);
        }
        JsonNode hookName = node.get("hook_name");
        if (hookName == null || !"merge_request_hooks".equals(hookName.asText())) {
            return null;
        }

        try {
            JsonNode action = node.get("pull_request");
            String owner = action.get("base").get("repo").get("namespace").asText();
            Integer id = action.get("number").asInt();
            String repoName = action.get("base").get("repo").get("path").asText();
            return PullRequest.of(id, owner, repoName);
        } catch (Exception e) {
            throw new NotFoundPullRequestException("fail to get pr obj, cause: " + e.getMessage());
        }
    }
}
