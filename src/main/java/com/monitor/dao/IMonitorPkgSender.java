package com.monitor.dao;

import java.util.List;

import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;

public interface IMonitorPkgSender {
    /**
     * save monitor pkg.
     * @param pkgList list of pkg.
     */
    void saveMonitorPkg(List<AbstractMonitorPkg> pkgList);
}
