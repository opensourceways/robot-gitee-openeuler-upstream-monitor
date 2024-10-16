package com.monitor.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.reflect.Constructor;

import com.monitor.dao.MonitorPkgSender;
import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;
import com.monitor.domain.monitorpkg.model.GithubMonitorPkg;
import com.monitor.monitor.MonitorApplication;

@SpringBootTest(classes = MonitorApplication.class)
public class MyTest {
    @Autowired
    private MonitorPkgSender sender;
    @Test
    public void test() throws Exception {
        Constructor<? extends AbstractMonitorPkg> c = GithubMonitorPkg.class.getConstructor();
        AbstractMonitorPkg pkg = c.newInstance();
        pkg.validPkg();
        System.out.println();
    }
}
