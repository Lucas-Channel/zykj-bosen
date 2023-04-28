package com.bosen.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池配置
 * 线程池有四大拒绝策略：
 *      AbortPolicy（默认）：丢弃任务并抛出 RejectedExecutionException 异常。
 *      CallerRunsPolicy：由调用主线程处理该任务。
 *      DiscardPolicy：丢弃任务，但是不抛出异常。可以配合这种模式进行自定义的处理方式。
 *      DiscardOldestPolicy：丢弃队列最早的未处理任务，然后重新尝试执行任务。
 * 也可以自定义拒绝策略，需要实现RejectedExecutionHandler
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/17
 */
@Configuration
public class ThreadPoolConfig {

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
    @Bean("defaultPoolExecutor")
    public ThreadPoolTaskExecutor defaultPoolExecutor() {
        ThreadPoolTaskExecutor defaultPoolExecutor = commonPoolExecutor();
        defaultPoolExecutor.setThreadNamePrefix("defaultPoolExecutor--");
        defaultPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        defaultPoolExecutor.initialize();
        return defaultPoolExecutor;
    }

    /**
     * 发送短信线程池
     **/
    @Bean("sendSmsThreadPool")
    public ThreadPoolTaskExecutor sendSmsThreadPool() {
        ThreadPoolTaskExecutor sendSmsThreadPool = commonPoolExecutor();
        sendSmsThreadPool.setThreadNamePrefix("sendSmsThreadPool--");
        sendSmsThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        sendSmsThreadPool.initialize();
        return sendSmsThreadPool;
    }

    /**
     * 同步数据线程池
     **/
    @Bean("syncDataThreadPool")
    public ThreadPoolTaskExecutor syncDataThreadPool() {
        ThreadPoolTaskExecutor syncDataThreadPool = commonPoolExecutor();
        syncDataThreadPool.setThreadNamePrefix("syncDataThreadPool--");
        syncDataThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        syncDataThreadPool.initialize();
        return syncDataThreadPool;
    }

}
