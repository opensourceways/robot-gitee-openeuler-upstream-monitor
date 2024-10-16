package com.monitor.common.utils;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil {
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * client.
     */
    @Autowired
    private CloseableHttpClient client;

    /**
     * http client response handler.
     */
    private static HttpClientResponseHandler<String> handler = response -> {
        int status = response.getCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        } else {
            throw new IOException("Unexpected response status: " + status);
        }
    };

    /**
     * get string from url.
     * @param url url.
     * @return string.
     * @throws IOException IOException.
     */
    public String getStringFromUrl(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return client.execute(httpGet, handler);

    }

    /**
     * post request with authorization.
     * @param url url.
     * @param body request body.
     * @param authorization authorization.
     * @return response.
     */
    public String postStringBodyWithAuthorization(String url, String body, String authorization) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", authorization);
        StringEntity stringEntity = new StringEntity(body);
        httpPost.setEntity(stringEntity);
        try {
            return client.execute(httpPost, handler);
        } catch (IOException e) {
            LOGGER.error("fail to post, url: {}, body: {}, cause: {}", url, body, e.getMessage());
            return null;
        }
    }
}
