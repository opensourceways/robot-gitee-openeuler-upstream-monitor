package com.monitor.service.monitorpkg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.dao.IMonitorPkgSender;
import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;

@Service
public class MonitorService implements IMonitorService {
    /**
     * upstream yml handler.
     */
    @Autowired
    private IUpStreamYmlHandler upStreamYmlHandler;

    /**
     * monitor pkg sender.
     */
    @Autowired
    private IMonitorPkgSender monitorPkgSender;

    /**
     * update upstream.
     */
    @Override
    public void updateUpstream(String requestBody) {
        List<AbstractMonitorPkg> pkgList = upStreamYmlHandler.getUpdatedUpstream(requestBody);
        if (pkgList == null || pkgList.isEmpty()) {
            return;
        }
        monitorPkgSender.saveMonitorPkg(pkgList);
    }
}
