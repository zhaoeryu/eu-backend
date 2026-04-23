package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.common.enums.EnableFlag;
import cn.eu.common.utils.easyexcel.EasyExcelEnumConverter;
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
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 角色KEY */
    @ExcelProperty("角色key")
    @NotBlank(message = "{valid.SysRole.roleKey.notBlank}")
    private String roleKey;
    /** 角色名称 */
    @ExcelProperty("角色名称")
    @NotBlank(message = "{valid.sysRole.roleName.notBlank}")
    private String roleName;
    /** 角色描述 */
    @ExcelProperty("角色描述")
    private String description;
    /**
     * 角色状态
     */
    @ExcelProperty(value = "状态", converter = EasyExcelEnumConverter.class)
    private EnableFlag status;
    /**
     * 数据权限
     */
    @ExcelIgnore
    private Integer dataScope;

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
