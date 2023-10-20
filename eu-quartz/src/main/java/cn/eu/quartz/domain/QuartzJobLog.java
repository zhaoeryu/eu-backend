package cn.eu.quartz.domain;

import cn.eu.common.base.domain.BaseEntity;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author zhaoeryu
 * @since 2023/6/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuartzJobLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    @ExcelProperty("ID")
    private String id;
    /**
     * 任务ID
     */
    @ExcelProperty("任务ID")
    private String jobId;
    /**
     * 任务名称
     */
    @ExcelProperty("任务名称")
    private String jobName;
    /**
     * 任务执行类
     */
    @ExcelProperty("任务执行类")
    private String invokeClassName;
    /**
     * 任务执行类的SpringBean名
     */
    @ExcelProperty("任务执行类的SpringBean名")
    private String springBeanName;
    /**
     * 任务执行方法
     */
    @ExcelProperty("任务执行方法")
    private String methodName;
    /**
     * 任务执行方法参数
     */
    @ExcelProperty("任务执行方法参数")
    private String methodParams;

    /**
     * 是否执行成功
     */
    @ExcelProperty("是否执行成功")
    private Boolean success;

    /**
     * 异常消息
     */
    @ExcelProperty("异常消息")
    private String exceptionMessage;
    /**
     * 异常详情
     */
    @ExcelProperty("异常详情")
    private String exceptionDetail;

    /**
     * 开始执行时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("开始执行时间")
    private LocalDateTime startTime;

    /**
     * 结束执行时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("结束执行时间")
    private LocalDateTime endTime;

    /**
     * 执行时长，单位：毫秒
     */
    @ExcelProperty("执行时长(ms)")
    private Long execTime;

}
