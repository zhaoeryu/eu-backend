package cn.eu.system.service.impl;

import cn.eu.common.base.service.impl.EuServiceImpl;
import cn.eu.common.enums.SysRoleStatus;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.EasyExcelHelper;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.common.utils.ValidateUtil;
import cn.eu.common.utils.easyexcel.EasyExcelWriteSheet;
import cn.eu.system.domain.*;
import cn.eu.system.mapper.SysRoleMapper;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.dto.SysRoleDto;
import cn.eu.system.model.query.SysRoleQueryCriteria;
import cn.eu.system.service.ISysRoleService;
import cn.eu.system.utils.ImportModeHandleTemplate;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Service
public class SysRoleServiceImpl extends EuServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public PageResult<SysRole> page(SysRoleQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysRole> list(SysRoleQueryCriteria criteria) {
        QueryWrapper<SysRole> queryWrapper = MpQueryHelper.buildQueryWrapperWithDelFlag(criteria, SysRole.class, "r");
        return sysRoleMapper.selectRoleList(queryWrapper);
    }

    @Override
    public List<String> getRolePermissionByUserId(String userId) {
        List<SysRole> roles = sysRoleMapper.getRolePermissionByUserId(userId, SysRoleStatus.NORMAL.getValue());
        return roles.stream()
                .map(SysRole::getRoleKey)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<SysRole> getRolesByUserId(String userId) {
        return sysRoleMapper.getRolesByUserId(userId, SysRoleStatus.NORMAL.getValue());
    }

    @Override
    public List<Integer> getRoleIdsByUserId(String userId) {
        List<SysRole> roles = sysRoleMapper.getRolesByUserId(userId, SysRoleStatus.NORMAL.getValue());
        return roles.stream()
                .map(SysRole::getId)
                .distinct()
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createRole(SysRoleDto entity) {
        save(entity);

        // 保存角色菜单关联
        if (CollUtil.isNotEmpty(entity.getMenuIds())) {
            List<SysRoleMenu> roleMenus = entity.getMenuIds().stream().map(menuId -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(entity.getId());
                sysRoleMenu.setMenuId(menuId);
                return sysRoleMenu;
            }).collect(Collectors.toList());
            sysRoleMapper.insertBatchRoleMenu(roleMenus);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(SysRoleDto entity) {
        updateById(entity);

        if (entity.getOperAction() == null) {
            return;
        }

        // 1: 本次操作是更新角色菜单关联
        if (entity.getOperAction() == 1) {
            // 删除角色菜单关联，并重新进行关联
            sysRoleMapper.deleteRoleMenuByRoleId(entity.getId());
            if (CollUtil.isNotEmpty(entity.getMenuIds())) {
                List<SysRoleMenu> roleMenus = entity.getMenuIds().stream().map(menuId -> {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(entity.getId());
                    sysRoleMenu.setMenuId(menuId);
                    return sysRoleMenu;
                }).collect(Collectors.toList());
                sysRoleMapper.insertBatchRoleMenu(roleMenus);
            }
        }

        // 2: 本次操作是更新角色部门关联
        if (entity.getOperAction() == 2) {
            // 删除角色部门关联，并重新进行关联
            sysRoleMapper.deleteRoleDeptByRoleId(entity.getId());
            if (CollUtil.isNotEmpty(entity.getDeptIds())) {
                List<SysRoleDept> roleDepts = entity.getDeptIds().stream().map(deptId -> {
                    SysRoleDept sysRoleDept = new SysRoleDept();
                    sysRoleDept.setRoleId(entity.getId());
                    sysRoleDept.setDeptId(deptId);
                    return sysRoleDept;
                }).collect(Collectors.toList());
                sysRoleMapper.insertBatchRoleDept(roleDepts);
            }
        }
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        List<SysRoleMenu> roleMenus = sysRoleMapper.getMenuIdsByRoleId(roleId);
        return roleMenus.stream()
                .map(SysRoleMenu::getMenuId)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public List<Integer> getDeptIdsByRoleId(Integer roleId) {
        List<SysRoleDept> roleMenus = sysRoleMapper.getDeptIdsByRoleId(roleId);
        return roleMenus.stream()
                .map(SysRoleDept::getDeptId)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void exportTemplate(HttpServletResponse response) throws IOException {
        List<String> exportExcludeFieldNames = buildExportExcludeFieldNames();

        // sheet1
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "sheet1")
                .head(SysRole.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        EasyExcelWriteSheet sheet1 = EasyExcelWriteSheet.of(writeSheet, null)
                .registerStandardWriteHandler();

        // sheet2
        writeSheet = EasyExcel.writerSheet(1, "示例数据")
                .head(SysRole.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        SysRole exampleEntity = buildExampleEntity();
        EasyExcelWriteSheet sheet2 = EasyExcelWriteSheet.of(writeSheet, Collections.singletonList(exampleEntity))
                .registerStandardWriteHandler();

        EasyExcelHelper.export(response, sheet1, sheet2);
    }

    @Override
    public ImportResult importData(MultipartFile file, Integer importMode) throws IOException {
        ImportModeHandleTemplate<SysRole, String> importModeHandleTemplate = new ImportModeHandleTemplate<SysRole, String>(importMode, SysRole::getRoleKey) {

            @Override
            protected void valid(List<SysRole> list) {
                list.forEach(ValidateUtil::valid);
            }

            @Override
            public void add(List<SysRole> list) {
                saveBatch(list, list.size());
            }

            @Override
            public void update(List<SysRole> list) {
                updateBatchById(list, list.size());
            }

            @Override
            protected SysRole buildUpdateItem(SysRole dbItem, SysRole excelItem) {
                SysRole updateItem = new SysRole();
                updateItem.setId(dbItem.getId());
                updateItem.setRoleKey(dbItem.getRoleKey());
                updateItem.setRoleName(excelItem.getRoleName());
                updateItem.setDescription(excelItem.getDescription());
                updateItem.setStatus(excelItem.getStatus());
                return updateItem;
            }

            @Override
            protected List<SysRole> getDbList(List<SysRole> list) {
                return list(new LambdaQueryWrapper<SysRole>()
                    .in(SysRole::getRoleKey, list.stream().map(SysRole::getRoleKey).collect(Collectors.toSet()))
                );
            }
        };
        EasyExcelHelper.importData(file, SysRole.class, importModeHandleTemplate::handle);

        return importModeHandleTemplate.getResult();
    }

    private List<String> buildExportExcludeFieldNames() {
        return Arrays.asList(
            "createTime",
            "remark"
        );
    }

    private SysRole buildExampleEntity() {
        SysRole entity = new SysRole();
        entity.setRoleKey("common");
        entity.setRoleName("普通用户");
        entity.setDescription("拥有网站的最基础功能");
        entity.setStatus(SysRoleStatus.NORMAL.getValue());
        return entity;
    }
}
