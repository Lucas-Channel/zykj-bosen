package com.bosen.workflow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
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
@MapperScan("com.bosen.workflow.mapper.**")
@EnableFeignClients(basePackages = "com.bosen.**.api")
@EnableProcessApplication("workflow-service")
@EnableDiscoveryClient
@SpringBootApplication
public class WorkFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkFlowApplication.class, args);
    }
}
