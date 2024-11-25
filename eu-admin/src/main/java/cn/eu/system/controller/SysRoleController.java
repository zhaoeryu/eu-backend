package cn.eu.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.eu.common.annotation.Log;
import cn.eu.common.core.controller.EuBaseController;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MessageUtils;
import cn.eu.system.domain.SysRole;
import cn.eu.system.model.dto.SysRoleDto;
import cn.eu.system.model.query.SysRoleQueryCriteria;
import cn.eu.system.service.ISysRoleService;
import cn.eu.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@RequestMapping("/api/system/role")
@RestController
public class SysRoleController extends EuBaseController {

    @Autowired
    ISysRoleService sysRoleService;
    @Autowired
    ISysUserService sysUserService;

    @Log(title = "查看角色列表", businessType = BusinessType.QUERY, isSaveResponseData = false)
    @SaCheckPermission(value = {"system:role:list", "system:user:list"}, mode = SaMode.OR)
    @GetMapping("/page")
    public ResultBody page(SysRoleQueryCriteria criteria, @PageableDefault(page = 1) Pageable pageable) {
        return ResultBody.ok().data(sysRoleService.page(criteria, pageable));
    }

    @Log(title = "新增角色", businessType = BusinessType.INSERT)
    @SaCheckPermission("system:role:add")
    @PostMapping
    public ResultBody save(@Validated @RequestBody SysRoleDto entity) {
        sysRoleService.createRole(entity);
        return ResultBody.ok();
    }

    @Log(title = "修改角色", businessType = BusinessType.UPDATE)
    @SaCheckPermission("system:role:edit")
    @PutMapping
    public ResultBody update(@Validated @RequestBody SysRoleDto entity) {
        Assert.notNull(entity.getId(), MessageUtils.message("assert.notNull", "id"));
        sysRoleService.updateRole(entity);
        return ResultBody.ok();
    }

    @Log(title = "删除角色", businessType = BusinessType.DELETE)
    @SaCheckPermission("system:role:del")
    @DeleteMapping("/batch")
    public ResultBody batchDelete(@RequestBody List<Integer> ids) {
        Assert.notEmpty(ids, MessageUtils.message("assert.notEmpty", "ids"));
        Assert.isTrue(sysUserService.countByRoleIds(ids) == 0, MessageUtils.message("assert.SysRole.existsConnection"));
        sysRoleService.removeByIds(ids);
        return ResultBody.ok();
    }

    @SaCheckPermission("system:role:list")
    @GetMapping("/menus")
    public ResultBody getRoleMenus(@RequestParam("roleId") Integer roleId) {
        return ResultBody.ok().data(sysRoleService.getMenuIdsByRoleId(roleId));
    }
    @SaCheckPermission("system:role:list")
    @GetMapping("/depts")
    public ResultBody getRoleDepts(@RequestParam("roleId") Integer roleId) {
        return ResultBody.ok().data(sysRoleService.getDeptIdsByRoleId(roleId));
    }

    @Log(title = "导出角色", businessType = BusinessType.EXPORT, isSaveResponseData = false)
    @SaCheckPermission("system:role:export")
    @PostMapping("/export")
    public void export(SysRoleQueryCriteria criteria, HttpServletResponse response) throws IOException {
        List<SysRole> list = sysRoleService.list(criteria);

        EasyExcelHelper.export(response, list, SysRole.class);
    }

    @Log(title = "下载导入角色模版", businessType = BusinessType.EXPORT_TEMPLATE, isSaveResponseData = false)
    @SaCheckPermission("system:role:import")
    @PostMapping("/export-template")
    public void exportTemplate(HttpServletResponse response) throws IOException {
        sysRoleService.exportTemplate(response);
    }

    @Log(title = "导入角色", businessType = BusinessType.IMPORT)
    @SaCheckPermission("system:role:import")
    @PostMapping("/import")
    public ResultBody importData(MultipartFile file, @RequestParam(value = "importMode", required = false) Integer importMode) throws IOException {
        return ResultBody.ok()
                .data(sysRoleService.importData(file, importMode));
    }
}
