package cn.eu.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Eu.z
 * @since 2024/7/14
 */
@Data
public class RoleDto implements Serializable {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 数据范围
     */
    private Integer dataScope;

}
