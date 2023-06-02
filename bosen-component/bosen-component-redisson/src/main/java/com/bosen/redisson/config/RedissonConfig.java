package com.bosen.redisson.config;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分布式锁 Redisson 配置
 */
@Data
@ConditionalOnProperty(prefix = "redisson",name = "address")
@ConfigurationProperties(prefix = "redisson")
@Configuration
public class RedissonConfig {

    /**
     * 是否集群
     */
    private Boolean clusterFlag;

    /**
     * 单机版正常填写ip，集群使用逗号拼接
     */
    private String address;

    private String password;

    private Integer database;

    private Integer minIdle;// 默认最小空闲连接数

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        if (clusterFlag) {
            List<String> clusterNodes = Arrays.stream(address.split(",")).map(i -> "redis://" + i).collect(Collectors.toList());
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            clusterServersConfig.addNodeAddress(String.valueOf(clusterNodes));
            if (StrUtil.isNotBlank(password)) {
                clusterServersConfig.setPassword(password);
            }
        } else {
            SingleServerConfig singleServerConfig = config.useSingleServer();
            singleServerConfig.setAddress("redis://" + address);
            singleServerConfig.setDatabase(database);
            singleServerConfig.setConnectionMinimumIdleSize(minIdle);
            if (StrUtil.isNotBlank(password)) {
                singleServerConfig.setPassword(password);
            }
        }

        return Redisson.create(config);
    }

}
