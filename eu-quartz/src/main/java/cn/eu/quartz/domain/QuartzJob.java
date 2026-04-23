package cn.eu.quartz.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.quartz.enums.QuartzJobMisfirePolicy;
import cn.eu.quartz.enums.QuartzJobStatus;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "创建人")
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private String createBy;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private String createByName;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    @ExcelIgnore
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateByName;

    @Schema(description = "更新时间")
    @ExcelIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(update = "now()")
    private LocalDateTime updateTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "删除标志(0:正常,NULL:删除)")
    @ExcelIgnore
    @TableLogic(value = "0", delval = "NULL")
    private Integer delFlag;


}
