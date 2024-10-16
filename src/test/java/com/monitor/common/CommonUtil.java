package com.monitor.common;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;


public class CommonUtil {
    public static void executeGet(MockMvc mockMvc, String url, MultiValueMap<String, String> paramMap) throws Exception {
        if (null == paramMap) {
            String content = mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        } else {
            String content = mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .params(paramMap)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        }
    }

    public static void executePost(MockMvc mockMvc, String url, String body) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body)
                .header(HttpHeaders.ACCEPT,"application/json"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }


    public static void assertLog(String path, String content) throws Exception {
        String log = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        long count = log.lines().filter(line -> line.contains(content)).count();
        assertNotEquals(count, 0);
    }
}
