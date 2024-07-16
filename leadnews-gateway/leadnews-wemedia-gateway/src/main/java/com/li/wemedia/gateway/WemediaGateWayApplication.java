package com.li.wemedia.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: liyuelian
 * @Date: 2024/7/16 15:43
 * @Description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class WemediaGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WemediaGateWayApplication.class, args);
    }
}
