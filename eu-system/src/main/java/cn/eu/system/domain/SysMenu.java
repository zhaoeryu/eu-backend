package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.common.enums.EnableFlag;
import cn.eu.common.enums.MenuType;
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
 * @since 2023/6/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 菜单名称 */
    private String menuName;
    /** 菜单图标 */
    private String menuIcon;
    /** 显示顺序 */
    private Integer sortNum;
    /** 权限标识 */
    private String permission;
    /** 路由地址 */
    private String path;
    /** 组件名称 */
    private String componentName;
    /** 组件路径 */
    private String component;
    /**
     * 菜单状态
     */
    private EnableFlag status;
    /** 是否固定 */
    private Boolean affix;
    /** 是否显示 */
    private Boolean visible;
    /** 是否缓存 */
    private Boolean cache;
    /** 是否内嵌 */
    private Boolean embed;
    private String embedUrl;
    /** 是否显示小红点 */
    private Boolean dot;
    /** 徽标内容 */
    private String badge;
    /**
     * 菜单类型
     */
    private MenuType menuType;
    /** 父菜单ID */
    private Integer parentId;
    /** 是否显示Header */
    private Boolean showHeader;
    /** 是否显示Footer */
    private Boolean showFooter;
    /**
     * 是否在只有一个子菜单时保持显示
     * true: 保持显示
     * false: 隐藏该菜单并在该菜单的位置显示该菜单的子菜单
     */
    private Boolean alwaysShow;

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
