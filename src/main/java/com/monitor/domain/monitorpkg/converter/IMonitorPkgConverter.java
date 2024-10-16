package com.monitor.domain.monitorpkg.converter;

import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;

public interface IMonitorPkgConverter {
    /**
     * convert raw yml file to monitor pkg.
     * @param rawFile raw file.
     * @return monitor pkg.
     * @throws Exception Exception.
     */
    AbstractMonitorPkg convertRawYmlFileToMonitorPkg(String rawFile) throws Exception;
}
