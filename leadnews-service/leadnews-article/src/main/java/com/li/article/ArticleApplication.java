package com.li.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: liyuelian
 * @Date: 2024/7/13 22:49
 * @Description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.li.article.mapper")
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
}
