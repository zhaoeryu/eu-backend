package cn.eu.config;

import cn.eu.properties.AsyncThreadProperties;
import cn.eu.properties.EuProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务线程池装配类
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncTaskExecutePool implements AsyncConfigurer {

    private final AsyncThreadProperties asyncThreadProperties;

    public AsyncTaskExecutePool(EuProperties euProperties) {
        this.asyncThreadProperties = euProperties.getThread().getAsync();
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncThreadProperties.getCorePoolSize());
        executor.setMaxPoolSize(asyncThreadProperties.getMaxPoolSize());
        executor.setQueueCapacity(asyncThreadProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(asyncThreadProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix("eu-async-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("exception method:" + method.getName());
            log.error("==== " + throwable.getMessage() + " ====", throwable);
        };
    }
}
