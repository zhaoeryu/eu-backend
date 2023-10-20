package cn.eu.system.mapper;

import cn.eu.common.annotation.DataScope;
import cn.eu.common.base.mapper.EuBaseMapper;
import cn.eu.system.domain.SysUser;
import cn.eu.system.domain.SysUserPost;
import cn.eu.system.domain.SysUserRole;
import cn.eu.system.model.dto.AssignRoleDto;
import cn.eu.system.model.query.AssignRoleQuery;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Mapper
public interface SysUserMapper extends EuBaseMapper<SysUser> {

    @DataScope(userAlias = "u", deptAlias = "d", userField = "id")
    List<SysUser> selectUserList(@Param(Constants.WRAPPER) Wrapper<SysUser> wrapper);

    void insertBatchUserPost(List<SysUserPost> posts);

    void insertBatchUserRole(List<SysUserRole> roles);

    void deleteUserPostByUserId(@Param("userId") String id);

    void deleteUserRoleByUserId(@Param("userId") String id);

    void cancelAuth(AssignRoleDto dto);

    @DataScope(userAlias = "u", deptAlias = "d", userField = "id")
    List<SysUser> selectRoleUserList(AssignRoleQuery query);

    int selectCountByRoleIds(@Param("roleIds") List<Integer> roleId);
    int selectCountByPostIds(@Param("postIds") List<Integer> postId);
}
