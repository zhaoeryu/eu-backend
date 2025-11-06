package cn.eu.system.service;

import cn.eu.common.core.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysUser;
import cn.eu.system.model.dto.AssignRoleDto;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.dto.SysUserDto;
import cn.eu.system.model.query.AssignRoleQuery;
import cn.eu.system.model.query.SysUserQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public interface ISysUserService extends IEuService<SysUser> {

    PageResult<SysUser> page(SysUserQueryCriteria criteria, Pageable pageable);

    List<SysUser> list(SysUserQueryCriteria criteria);

    /**
     * 创建用户
     * @param dto
     */
    String createUser(SysUserDto dto);

    void updateUser(SysUserDto dto);

    void assignRole(String userId, List<Integer> roleIds);

    PageResult<SysUser> roleUserList(AssignRoleQuery query, Pageable pageable);

    void cancelAuth(AssignRoleDto dto);

    void batchAssignRole(AssignRoleDto dto);

    int countByRoleIds(List<Integer> roleIds);
    Long countByDeptIds(List<Integer> deptId);
    int countByPostIds(List<Integer> postIds);

    ImportResult importData(MultipartFile file, Integer importMode) throws IOException;

    void exportTemplate(HttpServletResponse response) throws IOException;
}
