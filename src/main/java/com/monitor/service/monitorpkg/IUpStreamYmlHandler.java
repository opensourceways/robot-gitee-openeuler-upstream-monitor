package com.monitor.service.monitorpkg;

import java.util.List;

import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;

public interface IUpStreamYmlHandler  {
    /**
     * get updated upstream.
     * @param text text.
     * @return list of AbstractMonitorPkg.
     */
    List<AbstractMonitorPkg> getUpdatedUpstream(String text);
}
