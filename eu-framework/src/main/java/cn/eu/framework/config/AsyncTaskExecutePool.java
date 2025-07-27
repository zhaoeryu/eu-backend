package cn.eu.framework.config;

import cn.eu.common.exception.ServiceException;
import cn.eu.common.properties.AsyncThreadProperties;
import cn.eu.common.properties.EuProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

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
        // 设置任务装饰器
        executor.setTaskDecorator(new ContextCopyingDecorator());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("exception method:" + method.getName());
            log.error("==== " + throwable.getMessage() + " ====", throwable);
            throw new ServiceException(throwable.getMessage());
        };
    }

    // 自定义任务装饰器
    private static class ContextCopyingDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            // 获取主线程的请求上下文
            RequestAttributes context = RequestContextHolder.getRequestAttributes();
            return () -> {
                try {
                    // 将上下文绑定到子线程
                    RequestContextHolder.setRequestAttributes(context);
                    runnable.run();
                } finally {
                    // 清理子线程的上下文，防止内存泄漏
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        }
    }
}
