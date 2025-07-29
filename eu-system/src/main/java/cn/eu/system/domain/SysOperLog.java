package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.common.enums.BusinessStatus;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.utils.easyexcel.EasyExcelEnumConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

}
