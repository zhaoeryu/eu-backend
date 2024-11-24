package cn.eu.common.properties;

import lombok.Data;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Data
public class AsyncThreadProperties {

    /**
     * 核心线程池大小
     */
    private int corePoolSize = 1;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 50;

    /**
     * 队列容量
     */
    private int queueCapacity = 1000;

    /**
     * 线程空闲时存活时间 单位：s
     */
    private int keepAliveSeconds = 60;


}
