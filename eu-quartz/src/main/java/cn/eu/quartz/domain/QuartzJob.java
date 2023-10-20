package cn.eu.quartz.domain;

import cn.eu.common.base.domain.BaseEntity;
import cn.eu.quartz.enums.QuartzJobMisfirePolicy;
import cn.eu.quartz.enums.QuartzJobStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhaoeryu
 * @since 2023/6/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuartzJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private String id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组
     */
    private String jobGroup;
    /**
     * cron表达式
     */
    private String cron;
    /**
     * 任务状态
     * @see QuartzJobStatus#getValue()
     */
    private Integer status;
    /**
     * 执行策略
     * @see QuartzJobMisfirePolicy#getValue()
     */
    private Integer misfirePolicy;
    /**
     * 是否允许并发
     * @see cn.eu.quartz.enums.QuartzJobConcurrent#getValue()
     */
    private Integer concurrent;

    /**
     * 任务执行类
     */
    private String invokeClassName;
    /**
     * 任务执行类的SpringBean名
     */
    private String springBeanName;
    /**
     * 任务执行方法
     */
    private String methodName;
    /**
     * 任务执行方法参数
     */
    private String methodParams;
    /**
     * 失败后是否暂停
     */
    private Boolean pauseAfterFailure;
    /**
     * 任务失败后的告警邮箱
     */
    private String alarmEmail;

}
