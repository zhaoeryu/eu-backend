package cn.eu.system.mapper;

import cn.eu.common.annotation.DataScope;
import cn.eu.common.base.mapper.EuBaseMapper;
import cn.eu.system.domain.SysRole;
import cn.eu.system.domain.SysRoleDept;
import cn.eu.system.domain.SysRoleMenu;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Mapper
public interface SysRoleMapper extends EuBaseMapper<SysRole> {

    @DataScope(userAlias = "u", deptAlias = "d", userField = "id")
    List<SysRole> selectRoleList(@Param(Constants.WRAPPER) Wrapper<SysRole> queryWrapper);

    List<SysRole> getRolePermissionByUserId(@Param("userId") String userId, @Param("status") Integer status);

    List<SysRole> getRolesByUserId(@Param("userId") String userId, @Param("status") Integer status);

    void insertBatchRoleMenu(List<SysRoleMenu> roleMenus);

    void deleteRoleMenuByRoleId(Integer id);

    List<SysRoleMenu> getMenuIdsByRoleId(Integer roleId);
    List<SysRoleDept> getDeptIdsByRoleId(Integer roleId);

    void deleteRoleDeptByRoleId(Integer id);

    void insertBatchRoleDept(List<SysRoleDept> roleDepts);
}
