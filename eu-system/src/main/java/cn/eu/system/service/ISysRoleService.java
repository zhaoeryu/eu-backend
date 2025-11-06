package cn.eu.system.service;

import cn.eu.common.core.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysRole;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.dto.SysRoleDto;
import cn.eu.system.model.query.SysRoleQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
public interface ISysRoleService extends IEuService<SysRole> {

    PageResult<SysRole> page(SysRoleQueryCriteria criteria, Pageable pageable);

    List<SysRole> list(SysRoleQueryCriteria criteria);

    List<String> getRolePermissionByUserId(String userId);

    List<SysRole> getRolesByUserId(String userId);

    List<Integer> getRoleIdsByUserId(String userId);

    void createRole(SysRoleDto entity);

    void updateRole(SysRoleDto entity);

    List<Integer> getMenuIdsByRoleId(Integer roleId);
    List<Integer> getDeptIdsByRoleId(Integer roleId);

    void exportTemplate(HttpServletResponse response) throws IOException;

    ImportResult importData(MultipartFile file, Integer importMode) throws IOException;
}
