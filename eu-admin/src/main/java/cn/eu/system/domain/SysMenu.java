package cn.eu.system.domain;

import cn.eu.common.base.domain.BaseEntity;
import cn.eu.common.enums.MenuStatus;
import cn.eu.common.enums.MenuType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
     * @see MenuStatus#getValue()
     */
    private Integer status;
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
     * @see MenuType#getValue()
     */
    private Integer menuType;
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
}
