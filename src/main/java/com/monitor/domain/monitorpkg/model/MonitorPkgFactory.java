package com.monitor.domain.monitorpkg.model;

import com.monitor.common.exception.UnrecognizedBackendException;

public class MonitorPkgFactory {
    /**
     * get monitor pkg by backend.
     * @param backend backend.
     * @return monitor pkg.
     */
    public static Class<? extends AbstractMonitorPkg> getMonitorPkgByBackend(String backend) {
        if ("github".equalsIgnoreCase(backend)) {
            return GithubMonitorPkg.class;
        } else if ("gitlab".equalsIgnoreCase(backend)) {
            return GitLabMonitorPkg.class;
        } else {
            throw new UnrecognizedBackendException("unknown backend: " + backend);
        }
    }
}
