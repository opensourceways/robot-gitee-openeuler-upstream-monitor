package com.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.service.monitorpkg.IMonitorService;

@RestController
@RequestMapping(value = "/monitor")
public class MonitorController {
    /**
     * monitor service.
     */
    @Autowired
    private IMonitorService monitorService;

    /**
     * webhook post request.
     * @param requestBody request body.
     */
    @RequestMapping(value = "/gitee/webhook", method = RequestMethod.POST)
    public void giteeWebhook(@RequestBody String requestBody) {
        monitorService.updateUpstream(requestBody);
    }
}
