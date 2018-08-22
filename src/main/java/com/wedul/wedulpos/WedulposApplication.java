package com.wedul.wedulpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wedul.*", "com.wedul.common.interceptor"})
public class WedulposApplication {

    public static void main(String[] args) {
        SpringApplication.run(WedulposApplication.class, args);
    }
}