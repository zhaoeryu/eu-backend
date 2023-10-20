package cn.eu.system.model.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhaoeryu
 * @since 2023/8/1
 */
@Data
public class AssignRoleQuery {

    @NotNull(message = "角色id不能为空")
    private Integer roleId;
    /**
     * 是否查询已经拥有该角色的用户
     * 1: 是
     * 0: 否
     */
    @NotNull(message = "是否查询已经拥有该角色的用户不能为空")
    private Integer hasRole;

    private String nickname;
    private String mobile;

}
