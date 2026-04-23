package cn.eu.quartz.domain;

import cn.eu.common.core.domain.BaseEntity;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
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
