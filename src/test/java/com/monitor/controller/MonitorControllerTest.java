package com.monitor.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.monitor.common.CommonUtil;
import com.monitor.common.utils.HttpClientUtil;
import com.monitor.common.utils.ObjectMapperUtil;
import com.monitor.domain.monitorservice.MonitorProperties;
import com.monitor.monitor.MonitorApplication;

@SpringBootTest(classes = MonitorApplication.class)
@AutoConfigureMockMvc
public class MonitorControllerTest {
    @Autowired
    private HttpClientUtil clientUtil;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MonitorProperties monitorProperties;

    private MockMvc mockMvc;

    private String logPath = "/var/local/monitorupstream/log/spring.log";

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 正常情况：用户提交的pr修改yml文件并且yml文件符合要求，则将包放入监控服务。
     * yml文件要求：
     *  1. 包含三个字段：name, backend, upstream
     *  2. backend必须是`GitHub`，upstream必须是github网址
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        File file = new File("/var/local/monitorupstream/monitor/mock-webhook/normal.json");
        String body = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        CommonUtil.executePost(mockMvc, "/monitor/gitee/webhook", body);
        assertMonitor();
    }

    /**
     * 异常情况1. 用户提交的pr不包含yml。
     */
    @Test
    public void test_without_yml() throws Exception {
        FileUtils.writeStringToFile(new File(logPath)
                , "", StandardCharsets.UTF_8);
        File file = new File("/var/local/monitorupstream/monitor/mock-webhook/noyml.json");
        String body = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        CommonUtil.executePost(mockMvc, "/monitor/gitee/webhook", body);
        CommonUtil.assertLog(logPath, "no updated pkg, owner");
    }

    /**
     * 异常情况2. 用户提交的pr所修改的yml不符合要求。
     */
    @Test
    public void test_with_unqualified_yml() throws Exception {
        FileUtils.writeStringToFile(new File(logPath)
                , "", StandardCharsets.UTF_8);
        File file = new File("/var/local/monitorupstream/monitor/mock-webhook/unrecognizedyml.json");
        String body = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        CommonUtil.executePost(mockMvc, "/monitor/gitee/webhook", body);
        CommonUtil.assertLog(logPath, "fail to get monitor");
    }

    private void assertMonitor() throws Exception {
        String template = monitorProperties.getPkgVersionUrlTemplate();
        String url = String.format(template, "Xline-for-test-monitor");
        String res = clientUtil.getStringFromUrl(url);
        JsonNode node = ObjectMapperUtil.toJsonNode(res);
        int items = node.get("total_items").asInt();
        assertNotEquals(items, 0);
    }

}
