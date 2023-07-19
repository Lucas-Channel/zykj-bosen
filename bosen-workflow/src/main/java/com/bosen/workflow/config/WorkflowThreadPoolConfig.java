package com.bosen.workflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/18
 */
@Configuration
public class WorkflowThreadPoolConfig {
    private static final int maxPoolSize = Runtime.getRuntime().availableProcessors();
    private static final int queueCapacity = 1500;
    private static final int keepAliveSeconds = 60;

    private ThreadPoolTaskExecutor commonPoolExecutor() {
        ThreadPoolTaskExecutor commonPoolExecutor = new ThreadPoolTaskExecutor();
        commonPoolExecutor.setCorePoolSize(maxPoolSize);
        commonPoolExecutor.setMaxPoolSize(maxPoolSize * 2);
        commonPoolExecutor.setQueueCapacity(queueCapacity);
        commonPoolExecutor.setKeepAliveSeconds(keepAliveSeconds);
        commonPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        commonPoolExecutor.setAllowCoreThreadTimeOut(true);
        return commonPoolExecutor;
    }

    /**
     * 通用线程池配置
     **/
    @Bean("workflowPoolExecutor")
    @Primary
    public ThreadPoolTaskExecutor workflowPoolExecutor() {
        ThreadPoolTaskExecutor defaultPoolExecutor = commonPoolExecutor();
        defaultPoolExecutor.setThreadNamePrefix("workflowPoolExecutor--");
        defaultPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        defaultPoolExecutor.initialize();
        return defaultPoolExecutor;
    }
}
