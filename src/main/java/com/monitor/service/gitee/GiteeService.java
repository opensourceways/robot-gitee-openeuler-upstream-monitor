package com.monitor.service.gitee;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.common.exception.GiteeRequestException;
import com.monitor.common.utils.HttpClientUtil;
import com.monitor.domain.gitee.GiteeProperties;
import com.monitor.domain.gitee.PullRequest;

@Service
public class GiteeService implements IGiteeService {
    /**
     * gitee properties.
     */
    @Autowired
    private GiteeProperties giteeProperties;

    /**
     * client.
     */
    @Autowired
    private HttpClientUtil clientUtil;

    /**
     * get files from pull request.
     */
    @Override
    public String getFilesFromPr(PullRequest pr) {
        if (pr == null || pr.getId() == null || StringUtils.isBlank(pr.getOwner())
                || StringUtils.isAllBlank(pr.getRepo())) {
            return null;
        }

        String template = giteeProperties.getPrFileUrlTemplate();
        String url = String.format(template, pr.getOwner(), pr.getRepo(), pr.getId(), giteeProperties.getAccessToken());
        try {
            return clientUtil.getStringFromUrl(url);
        } catch (IOException e) {
            throw new GiteeRequestException("fail to get files from pr, cause: " + e.getMessage() + ", url: " + url);
        }

    }

    /**
     * get raw file.
     */
    @Override
    public String getRawFile(String owner, String repo, String path, String ref) {
        if (StringUtils.isBlank(owner) || StringUtils.isBlank(repo) || StringUtils.isBlank(path)
                || StringUtils.isBlank(ref)) {
            throw new IllegalArgumentException("some of parameters are null, owner: " + owner + ", repo: " + repo
                    + "path: " + path + "ref: " + ref);
        }

        String template = giteeProperties.getRawUrlTemplate();
        String url = String.format(template, owner, repo, path, giteeProperties.getAccessToken(), ref);
        try {
            return clientUtil.getStringFromUrl(url);
        } catch (IOException e) {
            throw new GiteeRequestException("fail to get raw files, cause: " + e.getMessage());
        }
    }

}
