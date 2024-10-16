package com.monitor.domain.monitorservice;

import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorServicePkg {
    /**
     * name.
     */
    private String name;

    /**
     * home page.
     */
    private String homepage;

    /**
     * backend.
     */
    private String backend;

    /**
     * create MonitorServicePkg from AbstractMonitorPkg.
     * @param pkg AbstractMonitorPkg.
     * @return MonitorServicePkg.
     */
    public static MonitorServicePkg ofMonitorPKg(AbstractMonitorPkg pkg) {
        MonitorServicePkg mPkg = new MonitorServicePkg();
        mPkg.setBackend(pkg.getBackend());
        mPkg.setName(pkg.getName());

        mPkg.setHomepage(pkg.getUpstream());
        return mPkg;
    }
}
