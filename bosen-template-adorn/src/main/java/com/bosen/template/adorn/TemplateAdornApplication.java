package com.bosen.template.adorn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 应用启动入口
 */
@MapperScan("com.bosen.template.adorn.mapper.**")
@ComponentScan(basePackages = {"com.bosen.common", "com.bosen.template.adorn"})
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class TemplateAdornApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateAdornApplication.class, args);
    }
}
