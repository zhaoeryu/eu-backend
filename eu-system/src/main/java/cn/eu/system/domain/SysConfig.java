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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 参数配置
 * @author zhaoeryu
 * @since 2026/7/6
 */
@Schema(description = "参数配置 - 实体类")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_config")
public class SysConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "参数主键")
    @ExcelIgnore
    @TableId
    private String configId;

    /**
     * 参数名称
     */
    @NotBlank(message = "{valid.SysConfig.configName.notBlank}")
    @ExcelProperty("参数名称")
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @NotBlank(message = "{valid.SysConfig.configKey.notBlank}")
    @ExcelProperty("参数键名")
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @ExcelProperty("参数键值")
    @Schema(description = "参数键值")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @ExcelProperty("系统内置")
    @Schema(description = "系统内置（Y是 N否）")
    private String configType;

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

    @Schema(description = "修改人")
    @ExcelIgnore
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @Schema(description = "修改人")
    @ExcelProperty("修改人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateByName;

    @Schema(description = "修改时间")
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
