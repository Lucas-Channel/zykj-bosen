package com.bosen.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @description: 网关实现限流
 *                  1、算法为令牌桶算法，主要有两部分组成：生产令牌和消费令牌
 *                  2、Spring Cloud Gatway内置的 RequestRateLimiterGatewayFilterFactory 提供限流的能力，基于令牌桶算法实现。
 *                  目前，它内置的 RedisRateLimiter ，依赖Redis存储限流配置，以及统计数据。当然你也可以实现自己的RateLimiter，
 *                  只需实现 org.springframework.cloud.gateway.filter.ratelimit.RateLimiter 接口，或者继承
 *                  org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter
 * @author: Lucas
 * @time: 2023/7/17 2:53 PM
 */
@Configuration
public class RequestRateLimiterConfig {
    /**
     * 方式一： 通过地址限流
     * @return 结果
     */
    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }

//    /**
//     * 通过用户限流
//     * @return
//     */
//    @Bean
//    public KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//    }
//
//    /**
//     * 通过ip限流
//     * @return
//     */
//    @Bean
//    public KeyResolver hostAddKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//    }
}
