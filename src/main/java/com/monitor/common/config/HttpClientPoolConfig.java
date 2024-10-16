package com.monitor.common.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientPoolConfig {
     /**
     * get ConnectionManager.
     * @return PoolingHttpClientConnectionManager.
     */
    public PoolingHttpClientConnectionManager getConnectionManager() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(200);
        return cm;
    }

    /**
     * get client.
     * @return HttpClient.
     */
    @Bean
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom().setConnectionManager(getConnectionManager()).build();
    }
}
