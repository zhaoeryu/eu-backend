package cn.eu.system.model.dto;

import cn.eu.system.domain.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDto extends SysRole {

    /**
     * 操作类型
     * 1: 菜单权限
     * 2: 数据权限
     */
    private Integer operAction;

    private List<Integer> menuIds;

    private List<Integer> deptIds;
}
