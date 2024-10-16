package com.monitor.domain.monitorservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "monitor")
public class MonitorProperties {
    /**
     * pkg post url template.
     */
    private String pkgPostUrlTemplate;

    /**
     * authorization.
     */
    private String authorization;

    /**
     * pkg version url template.
     */
    private String pkgVersionUrlTemplate;
}
