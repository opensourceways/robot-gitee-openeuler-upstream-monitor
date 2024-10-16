package com.monitor.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.common.utils.HttpClientUtil;
import com.monitor.common.utils.ObjectMapperUtil;
import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;
import com.monitor.domain.monitorservice.MonitorProperties;
import com.monitor.domain.monitorservice.MonitorServicePkg;

@Service
public class MonitorPkgSender implements IMonitorPkgSender {
    /**
     * client.
     */
    @Autowired
    private HttpClientUtil clientUtil;

    /**
     * monitor properties.
     */
    @Autowired
    private MonitorProperties monitorProperties;

    /**
     * save pkg list.
     */
    @Override
    public void saveMonitorPkg(List<AbstractMonitorPkg> pkgList) {
        String url = monitorProperties.getPkgPostUrlTemplate();
        String authorization = monitorProperties.getAuthorization();

        for (AbstractMonitorPkg pkg : pkgList) {
            MonitorServicePkg mPkg = MonitorServicePkg.ofMonitorPKg(pkg);
            String body = ObjectMapperUtil.writeValueAsString(mPkg);
            clientUtil.postStringBodyWithAuthorization(url, body, authorization);
        }
    }
}
