package cn.eu.system.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.constants.Constants;
import cn.eu.common.enums.*;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.*;
import cn.eu.common.utils.easyexcel.EasyExcelCellItem;
import cn.eu.common.utils.easyexcel.EasyExcelUtil;
import cn.eu.common.utils.easyexcel.EasyExcelWriteSheet;
import cn.eu.system.domain.SysDept;
import cn.eu.system.domain.SysUser;
import cn.eu.system.domain.SysUserPost;
import cn.eu.system.domain.SysUserRole;
import cn.eu.system.event.LoginCacheRefreshEvent;
import cn.eu.system.mapper.SysUserMapper;
import cn.eu.system.utils.ImportModeHandleTemplate;
import cn.eu.system.model.dto.AssignRoleDto;
import cn.eu.system.model.dto.ImportResult;
import cn.eu.system.model.dto.SysUserDto;
import cn.eu.system.model.query.AssignRoleQuery;
import cn.eu.system.model.query.SysUserQueryCriteria;
import cn.eu.system.service.ISysDeptService;
import cn.eu.system.service.ISysUserService;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Service
public class SysUserServiceImpl extends EuServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    ISysDeptService sysDeptService;

    @Override
    public PageResult<SysUser> page(SysUserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysUser> list(SysUserQueryCriteria criteria) {
        QueryWrapper<SysUser> queryWrapper = MpQueryHelper.buildQueryWrapperWithDelFlag(criteria, SysUser.class, "u");
        // 根据部门查询
        if (criteria.getDeptId() != null) {
            String sql;
            if (MpUtil.isPostgresql()) {
                sql = "u.dept_id in (select id from sys_dept where id = COALESCE(array_position(string_to_array(parent_ids, ',')::int[], {0}), 0))";
            } else {
                sql = "u.dept_id in (select id from sys_dept where find_in_set({0}, parent_ids))";
            }
            queryWrapper.and(i -> i.eq("u.dept_id", criteria.getDeptId())
                    .or()
                    .apply(sql, criteria.getDeptId()));
        }
        return sysUserMapper.selectUserList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createUser(SysUserDto dto) {
        String defaultPassword = PasswordGenerator.builder()
                .length(10)
                .useSpecial(false)
                .build()
                .generate();
        // 密码加密
        dto.setPassword(PasswordEncoder.encode(defaultPassword));

        // 保存用户信息
        save(dto);

        // 保存用户岗位关系
        Optional.ofNullable(dto.getPostIds()).ifPresent(postIds -> {
            List<SysUserPost> posts = postIds.stream().map(postId -> {
                SysUserPost sysUserPost = new SysUserPost();
                sysUserPost.setUserId(dto.getId());
                sysUserPost.setPostId(postId);
                return sysUserPost;
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(posts)) {
                sysUserMapper.insertBatchUserPost(posts);
            }
        });

        // 保存用户角色关系
        Optional.ofNullable(dto.getRoleIds()).ifPresent(roleIds -> {
            List<SysUserRole> roles = roleIds.stream().map(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(dto.getId());
                sysUserRole.setRoleId(roleId);
                return sysUserRole;
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(roles)) {
                sysUserMapper.insertBatchUserRole(roles);
            }
        });

        return defaultPassword;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(SysUserDto dto) {
        // 更新用户信息
        updateById(dto);

        // 删除并重新保存用户岗位关系
        sysUserMapper.deleteUserPostByUserId(dto.getId());
        Optional.ofNullable(dto.getPostIds()).ifPresent(postIds -> {
            List<SysUserPost> posts = postIds.stream().map(postId -> {
                SysUserPost sysUserPost = new SysUserPost();
                sysUserPost.setUserId(dto.getId());
                sysUserPost.setPostId(postId);
                return sysUserPost;
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(posts)) {
                sysUserMapper.insertBatchUserPost(posts);
            }
        });

        // 删除并重新保存用户角色关系
        sysUserMapper.deleteUserRoleByUserId(dto.getId());
        Optional.ofNullable(dto.getRoleIds()).ifPresent(roleIds -> {
            List<SysUserRole> roles = roleIds.stream().map(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(dto.getId());
                sysUserRole.setRoleId(roleId);
                return sysUserRole;
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(roles)) {
                sysUserMapper.insertBatchUserRole(roles);
            }
        });

        // 刷新缓存
        SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(dto.getId()));
    }

    @Override
    public void assignRole(String userId, List<Integer> roleIdList) {
        // 删除并重新保存用户角色关系
        sysUserMapper.deleteUserRoleByUserId(userId);
        Optional.ofNullable(roleIdList).ifPresent(roleIds -> {
            List<SysUserRole> roles = roleIds.stream().map(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                return sysUserRole;
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(roles)) {
                sysUserMapper.insertBatchUserRole(roles);
            }
        });
        // 更新缓存
        SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(userId));
    }

    @Override
    public PageResult<SysUser> roleUserList(AssignRoleQuery query, Pageable pageable) {
        getPage(pageable);
        List<SysUser> list = sysUserMapper.selectRoleUserList(query);
        return PageResult.of(list);
    }

    @Override
    public void cancelAuth(AssignRoleDto dto) {
        sysUserMapper.cancelAuth(dto);

        dto.getUserIds().forEach(userId -> {
            // 刷新缓存
            SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(userId));
        });
    }

    @Override
    public void batchAssignRole(AssignRoleDto dto) {
        List<SysUserRole> roles = dto.getUserIds().stream().map(userId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(dto.getRoleId());
            return sysUserRole;
        }).collect(Collectors.toList());
        sysUserMapper.insertBatchUserRole(roles);

        dto.getUserIds().forEach(userId -> {
            // 刷新缓存
            SpringContextHolder.getApplicationContext().publishEvent(new LoginCacheRefreshEvent(userId));
        });
    }

    @Override
    public int countByRoleIds(List<Integer> roleIds) {
        return sysUserMapper.selectCountByRoleIds(roleIds);
    }

    @Override
    public Long countByDeptIds(List<Integer> deptIds) {
        return count(new LambdaQueryWrapper<SysUser>()
            .in(SysUser::getDeptId, deptIds)
        );
    }

    @Override
    public int countByPostIds(List<Integer> postIds) {
        return sysUserMapper.selectCountByPostIds(postIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportResult importData(MultipartFile file, Integer importMode) throws IOException {
        ImportModeHandleTemplate<SysUser, String> importModeHandleTemplate = new ImportModeHandleTemplate<SysUser, String>(importMode, SysUser::getUsername) {

            @Override
            protected void valid(List<SysUser> list) {
                // 校验数据
                list.forEach(item -> {
                    // 校验
                    ValidateUtil.valid(item);
                    // 填充默认数据
                    item.setPassword(PasswordEncoder.encode(PasswordGenerator.buildInitialPassword(item.getUsername(), item.getMobile())));
                    item.setAdmin(BooleanFlag.FALSE);
                    item.setStatus(SysUserStatus.NORMAL);
                });
            }

            @Override
            public void add(List<SysUser> list) {
                saveBatch(list, list.size());
            }

            @Override
            public void update(List<SysUser> list) {
                updateBatchById(list, list.size());
            }

            @Override
            protected SysUser buildUpdateItem(SysUser dbItem, SysUser excelItem) {
                SysUser updateItem = new SysUser();
                updateItem.setId(dbItem.getId());
                updateItem.setUsername(dbItem.getUsername());
                updateItem.setNickname(excelItem.getNickname());
                updateItem.setMobile(excelItem.getMobile());
                updateItem.setEmail(excelItem.getEmail());
                updateItem.setSex(excelItem.getSex());
                updateItem.setDeptId(excelItem.getDeptId());
                updateItem.setRemark(excelItem.getRemark());
                return updateItem;
            }

            @Override
            protected List<SysUser> getDbList(List<SysUser> list) {
                return list(new LambdaQueryWrapper<SysUser>()
                        .in(SysUser::getUsername, list.stream().map(SysUser::getUsername).collect(Collectors.toSet()))
                );
            }
        };

        EasyExcelHelper.importData(file, SysUser.class, importModeHandleTemplate::handle);

        // Tips: 这里根据实际情况做一些特殊的处理，解决导入后怎么让用户知道系统生成的密码。
        // 现在的处理是根据特定的规则生成密码，然后管理员根据规则把登录账号和密码告诉用户。
        // 1、通过短信发送初始密码给用户
        // 2、通过邮件发送初始密码给用户
        // 3、生成一个初始密码的链接给用户，用户点击链接后重置密码
        return importModeHandleTemplate.getResult();
    }

    @Override
    public void exportTemplate(HttpServletResponse response) throws IOException {
        // 查询系统里的部门，并按照部门层级拼接部门名称
        List<SysDept> deptList = sysDeptService.listWithConcatenatedDeptName();

        List<String> exportExcludeFieldNames = buildExportExcludeFieldNames();

        // sheet1
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "sheet1")
                .head(SysUser.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        EasyExcelWriteSheet sheet1 = EasyExcelWriteSheet.of(writeSheet, null)
                .registerStandardWriteHandler();

        // sheet2
        writeSheet = EasyExcel.writerSheet(1, "示例数据")
                .head(SysUser.class)
                .excludeColumnFieldNames(exportExcludeFieldNames)
                .build();
        SysUser exampleUser = buildExampleUser();
        if (deptList.size() > 0) {
            exampleUser.setDeptId(deptList.get(0).getId());
        }
        EasyExcelWriteSheet sheet2 = EasyExcelWriteSheet.of(writeSheet, Collections.singletonList(exampleUser))
                .registerStandardWriteHandler();

        // sheet3
        writeSheet = EasyExcel.writerSheet(2, "部门选项")
                .registerWriteHandler(EasyExcelUtil.headCellStyleStrategy())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .build();
        EasyExcelWriteSheet sheet3 = EasyExcelWriteSheet.of(writeSheet, deptList, Arrays.asList(
            EasyExcelCellItem.of("部门名称", SysDept::getDeptName)
        ));

        // 导出
        EasyExcelHelper.export(response,
            sheet1,
            sheet2,
            sheet3
        );
    }

    private List<String> buildExportExcludeFieldNames() {
        return Arrays.asList(
                "avatar",
                "admin",
                "status",
                "loginIp",
                "loginTime",
                "lastActiveTime",
                "passwordResetTime",
                "createTime"
        );
    }

    private SysUser buildExampleUser() {
        SysUser exampleUser = new SysUser();
        exampleUser.setUsername("zhangsan");
        exampleUser.setNickname("张三");
        exampleUser.setMobile("18500000000");
        exampleUser.setEmail("zhangsan@eu.com");
        exampleUser.setSex(SysUserSex.MAN);
        exampleUser.setDeptId(1);
        exampleUser.setRemark("这里可以写一些备注");
        return exampleUser;
    }

}
