package cn.eu.system.domain;

import cn.eu.common.base.domain.BaseEntity;
import cn.eu.common.enums.BusinessStatus;
import cn.eu.system.easyexcel.converter.BusinessStatusConverter;
import cn.eu.system.easyexcel.converter.BusinessTypeConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalDate;

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
    @NotBlank(message = "操作模块不能为空")
    @ExcelProperty("操作模块")
    private String title;

    /**
     * 业务类型
     */
    @NotNull(message = "业务类型不能为空")
    @ExcelProperty(value = "业务类型", converter = BusinessTypeConverter.class)
    private Integer businessType;

    /**
     * 执行方法
     */
    @NotBlank(message = "执行方法不能为空")
    @ExcelProperty("执行方法")
    private String method;

    /**
     * Http请求方式
     */
    @NotBlank(message = "Http请求方式不能为空")
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
    @NotBlank(message = "请求URL不能为空")
    @ExcelProperty("请求URL")
    private String reqUrl;

    /**
     * 请求IP
     */
    @NotBlank(message = "请求IP不能为空")
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
    @NotNull(message = "操作状态不能为空")
    @ExcelProperty(value = "操作状态", converter = BusinessStatusConverter.class)
    private Integer status;

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
    @NotNull(message = "执行时长不能为空")
    @ExcelProperty("执行时长(ms)")
    private Long execTime;

}
