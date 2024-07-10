package com.li.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: liyuelian
 * @Date: 2024/7/10 20:13
 * @Description:
 **/

@SpringBootApplication
@EnableDiscoveryClient //开启注册中心
public class AppGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGateWayApplication.class,args);
    }
}
