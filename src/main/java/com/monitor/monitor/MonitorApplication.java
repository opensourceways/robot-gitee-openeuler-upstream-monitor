package com.monitor.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.monitor.*"})
public class MonitorApplication {
    /**
     * run.
     * @param args args.
     */
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

}
