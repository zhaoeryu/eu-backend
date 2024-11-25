package cn.eu.system.domain;

import cn.eu.common.core.domain.BaseEntity;
import cn.eu.common.enums.SysRoleStatus;
import cn.eu.system.easyexcel.converter.SysRoleStatusConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

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
     * @see SysRoleStatus#getValue()
     */
    @ExcelProperty(value = "角色状态", converter = SysRoleStatusConverter.class)
    private Integer status;
    /**
     * 数据权限
     */
    @ExcelIgnore
    private Integer dataScope;
}
