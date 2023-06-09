package com.bosen.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.bosen.auth.mapper.**")
@ComponentScan(basePackages = {"com.bosen.**"})
@EnableFeignClients(basePackages = "com.bosen.**.api")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.bosen.auth")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
