package com.monitor.domain.monitorpkg.model;

import org.apache.commons.lang3.StringUtils;

import com.monitor.common.utils.SpringContextUtil;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubMonitorPkg extends AbstractMonitorPkg {
    /**
     * valid pkg.
     */
    @Override
    public boolean validPkg() {
        List<String> errList = new ArrayList<>();
        if (StringUtils.isBlank(name)) {
            errList.add("no name");
        }
        if (StringUtils.isBlank(upstream)) {
            errList.add("no upstream");
        }
        if (StringUtils.isBlank(backend)) {
            errList.add("no backend");
        }

        GithubMonitorProperties properties = SpringContextUtil.getBean(GithubMonitorProperties.class);

        if ((upstream != null && !upstream.startsWith(properties.getUpstreamPrefix()))
                || !properties.getBackend().equals(backend)) {
            errList.add("un supported upstream");
        }

        if (errList.isEmpty()) {
            return true;
        }
        throw new IllegalArgumentException("pkg: " + this.toString() + ", err: " + errList);
    }
}
