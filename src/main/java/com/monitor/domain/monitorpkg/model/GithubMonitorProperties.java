package com.monitor.domain.monitorpkg.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "allowed-pkg.github")
public class GithubMonitorProperties {
    /**
     * backend.
     */
    private String backend;

    /**
     * upstrema prefix.
     */
    private String upstreamPrefix;
}
