package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.common.enums.BusinessStatus;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.utils.easyexcel.EasyExcelEnumConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 操作日志
 * @author zhaoeryu
 * @since 2023/07/11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oper_log")
public class SysOperLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    @ExcelProperty("id")
    private String id;

    /**
     * 操作模块
     */
    @NotBlank(message = "{valid.SysOperLog.title.notBlank}")
    @ExcelProperty("操作模块")
    private String title;

    /**
     * 业务类型
     */
    @NotNull(message = "{valid.SysOperLog.businessType.notNull}")
    @ExcelProperty(value = "业务类型", converter = EasyExcelEnumConverter.class)
    private BusinessType businessType;

    /**
     * 执行方法
     */
    @NotBlank(message = "{valid.SysOperLog.method.notBlank}")
    @ExcelProperty("执行方法")
    private String method;

    /**
     * Http请求方式
     */
    @NotBlank(message = "{valid.SysOperLog.reqMethod.notBlank}")
    @ExcelProperty("Http请求方式")
    private String reqMethod;

    /**
     * 操作人名称
     */
    @ExcelProperty("操作人名称")
    private String operName;

    /**
     * 操作人部门
     */
    @ExcelProperty("操作人部门")
    private String deptName;

    /**
     * 请求URL
     */
    @NotBlank(message = "{valid.SysOperLog.reqUrl.notBlank}")
    @ExcelProperty("请求URL")
    private String reqUrl;

    /**
     * 请求IP
     */
    @NotBlank(message = "{valid.SysOperLog.reqIp.notBlank}")
    @ExcelProperty("请求IP")
    private String reqIp;

    /**
     * 请求地域
     */
    @ExcelProperty("请求地域")
    private String reqRegion;

    /**
     * 请求参数
     */
    @ExcelProperty("请求参数")
    private String reqParams;

    /**
     * 响应结果
     */
    @ExcelProperty("响应结果")
    private String respResult;

    /**
     * 浏览器
     */
    @ExcelProperty("浏览器")
    private String browser;

    /**
     * 操作系统
     */
    @ExcelProperty("操作系统")
    private String os;

    /**
     * 操作状态
     */
    @NotNull(message = "{valid.SysOperLog.status.notNull}")
    @ExcelProperty(value = "操作状态", converter = EasyExcelEnumConverter.class)
    private BusinessStatus status;

    /**
     * 错误消息
     */
    @ExcelProperty("错误消息")
    private String errorMsg;

    /**
     * 异常堆栈
     */
    @ExcelProperty("异常堆栈")
    private String errorStack;

    /**
     * 执行时长
     */
    @NotNull(message = "{valid.SysOperLog.execTime.notNull}")
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
