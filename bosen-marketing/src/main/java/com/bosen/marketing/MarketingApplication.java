package com.bosen.marketing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/9/12
 */
@ComponentScan(basePackages = {"com.bosen.**"})
@MapperScan("com.bosen.marketing.mapper.**")
@EnableFeignClients(basePackages = "com.bosen.**.api")
@EnableDiscoveryClient
@SpringBootApplication
public class MarketingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketingApplication.class, args);
    }
}
