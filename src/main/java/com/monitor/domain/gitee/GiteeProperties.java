package com.monitor.domain.gitee;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "gitee")
public class GiteeProperties {
    /**
     * access token.
     */
    private String accessToken;

    /**
     * pr file url template.
     */
    private String prFileUrlTemplate;

    /**
     * raw url template.
     */
    private String rawUrlTemplate;
}
