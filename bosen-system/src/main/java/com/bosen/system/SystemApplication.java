package com.bosen.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 应用启动入口
 */
@ComponentScan(basePackages = {"com.bosen.**"})
@MapperScan("com.bosen.system.mapper.**")
@EnableFeignClients(basePackages = "com.bosen.**.api")
@EnableDiscoveryClient
@SpringBootApplication
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
