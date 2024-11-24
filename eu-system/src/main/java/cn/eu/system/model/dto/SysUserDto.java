package cn.eu.system.model.dto;

import cn.eu.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDto extends SysUser {

    /**
     * 岗位ID列表
     */
    private List<Integer> postIds;
    /**
     * 角色ID列表
     */
    private List<Integer> roleIds;

}
