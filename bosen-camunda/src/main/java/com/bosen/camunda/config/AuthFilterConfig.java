package com.bosen.camunda.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.camunda.bpm.client.spring.boot.starter.ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 目前camunda的rest api的接口是不需要授权的，所以为了接口安全，需要设置鉴权
 * yml设置basic-username/password,同事需要配置feign.client.config的参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/21
 */
@Configuration
public class AuthFilterConfig {

    @Resource
    private ClientProperties clientProperties;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(clientProperties.getBasicAuth().getUsername(), clientProperties.getBasicAuth().getPassword());
    }
}
