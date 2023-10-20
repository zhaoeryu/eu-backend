package cn.eu.system.domain;

import cn.eu.common.base.domain.BaseEntity;
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
 * 通知公告
 * @author Eu.z
 * @since 2023/08/30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_notice")
public class SysNotice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    @ExcelIgnore
    private String id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    @ExcelProperty("标题")
    private String title;

    /**
     * 公告类型
     */
    @NotNull(message = "公告类型不能为空")
    @ExcelProperty("公告类型")
    private Integer type;

    /**
     * 公告描述
     */
    @NotBlank(message = "公告描述不能为空")
    @ExcelProperty("公告描述")
    private String description;

    /**
     * 公告内容
     */
    @NotBlank(message = "公告内容不能为空")
    @ExcelProperty("公告内容")
    private String content;

    /**
     * 公告状态
     */
    @NotNull(message = "公告状态不能为空")
    @ExcelProperty("公告状态")
    private Integer status;

    @ExcelProperty("发布人")
    private String publisher;

}
