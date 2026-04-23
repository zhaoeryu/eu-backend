package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
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
    @NotBlank(message = "{valid.SysNotice.title.notBlank}")
    @ExcelProperty("标题")
    private String title;

    /**
     * 公告类型
     */
    @NotNull(message = "{valid.SysNotice.type.notNull}")
    @ExcelProperty("公告类型")
    private Integer type;

    /**
     * 公告描述
     */
    @NotBlank(message = "{valid.SysNotice.description.notBlank}")
    @ExcelProperty("公告描述")
    private String description;

    /**
     * 公告内容
     */
    @NotBlank(message = "{valid.SysNotice.content.notBlank}")
    @ExcelProperty("公告内容")
    private String content;

    /**
     * 公告状态
     */
    @NotNull(message = "{valid.SysNotice.status.notNull}")
    @ExcelProperty("公告状态")
    private Integer status;

    @ExcelProperty("发布人")
    private String publisher;

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
