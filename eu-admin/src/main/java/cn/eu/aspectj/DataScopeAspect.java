package cn.eu.aspectj;

import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.annotation.DataScope;
import cn.eu.common.enums.DataScopeType;
import cn.eu.common.utils.DataScopeContextHelper;
import cn.eu.security.SecurityUtil;
import cn.eu.common.model.AuthUser;
import cn.eu.system.domain.SysRole;
import cn.eu.system.service.ISysRoleService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/8/3
 */
@Slf4j
@Aspect
@Component
public class DataScopeAspect {

    @Autowired
    ISysRoleService sysRoleService;

    @Before("@annotation(dataScope)")
    public void doBefore(JoinPoint joinPoint, DataScope dataScope) throws Throwable {
        // 对@DataScope注解的方法前执行，拼接SQL
        log.debug("before dataScope: {}/{}", dataScope.deptAlias(), dataScope.userAlias());
        if (!StpUtil.isLogin()) {
            // 如果没有登录，不需要拼接SQL
            log.debug("not login, no need to set dataScope");
            return;
        }
        if (SecurityUtil.isAdminLogin()) {
            // 如果是超级管理员，拥有所有权限，不需要拼接SQL
            log.debug("admin login, no need to set dataScope");
            return;
        }
        AuthUser loginUser = SecurityUtil.getLoginUser();
        List<SysRole> sysRoles = SecurityUtil.getLoginUserRoles();

        StringBuilder sqlBuilder = new StringBuilder();
        List<Integer> conditions = CollUtil.newArrayList();

        for (SysRole sysRole : sysRoles) {
            Integer scope = sysRole.getDataScope();
            if (scope == null) {
                // 如果该角色没有设置数据权限，跳过
                continue;
            }
            DataScopeType scopeType = DataScopeType.parseValue(scope);
            if (scopeType == null) {
                // 如果该角色的数据权限设置不正确，跳过
                log.warn("未知的数据权限类型: {}", scope);
                continue;
            }
            if (DataScopeType.DATA_SCOPE_CUSTOM != scopeType && conditions.contains(scope)) {
                // 如果该角色的数据权限已经设置过，跳过（自定义权限除外）
                continue;
            }

            switch (scopeType) {
                case DATA_SCOPE_ALL:
                    // 数据范围：全部数据权限
                    log.debug("dataScope: all");
                    return;
                case DATA_SCOPE_CUSTOM:
                    // 数据范围：自定义数据权限
                    if (dataScope.isSingleQuery()) {
                        sqlBuilder.append(StrUtil.format(
                                " OR id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ",
                                sysRole.getId()
                        ));
                    } else if (StrUtil.isNotBlank(dataScope.deptAlias())) {
                        sqlBuilder.append(StrUtil.format(
                                " OR {}.id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ",
                                dataScope.deptAlias(),
                                sysRole.getId()
                        ));
                    } else {
                        log.warn("dataScope.deptAlias() 未设置 deptAlias");
                        continue;
                    }
                    break;
                case DATA_SCOPE_DEPT_AND_CHILD:
                    // 数据范围：本部门及以下数据权限
                    if (dataScope.isSingleQuery()) {
                        sqlBuilder.append(StrUtil.format(
                                " OR id IN ( SELECT id FROM sys_dept WHERE id = {} OR find_in_set( {} , parent_ids ) ) ",
                                loginUser.getDeptId(),
                                loginUser.getDeptId()
                        ));
                    } else if (StrUtil.isNotBlank(dataScope.deptAlias())) {
                        sqlBuilder.append(StrUtil.format(
                                " OR {}.id IN ( SELECT id FROM sys_dept WHERE id = {} OR find_in_set( {} , parent_ids ) ) ",
                                dataScope.deptAlias(),
                                loginUser.getDeptId(),
                                loginUser.getDeptId()
                        ));
                    } else {
                        log.warn("dataScope.deptAlias() 未设置 deptAlias");
                        continue;
                    }
                    break;
                case DATA_SCOPE_DEPT:
                    // 数据范围：本部门数据权限
                    if (dataScope.isSingleQuery()) {
                        sqlBuilder.append(StrUtil.format(
                                " OR id = {} ",
                                loginUser.getDeptId()
                        ));
                    } else if (StrUtil.isNotBlank(dataScope.deptAlias())) {
                        sqlBuilder.append(StrUtil.format(
                                " OR {}.id = {} ",
                                dataScope.deptAlias(),
                                loginUser.getDeptId()
                        ));
                    } else {
                        log.warn("dataScope.deptAlias() 未设置 deptAlias");
                        continue;
                    }
                    break;
                case DATA_SCOPE_SELF:
                    // 数据范围：仅本人数据权限
                    if (StrUtil.isNotBlank(dataScope.userAlias())) {
                        if (StrUtil.isNotBlank(dataScope.userField())) {
                            if (dataScope.isSingleQuery()) {
                                sqlBuilder.append(StrUtil.format(
                                        " OR {} = {} ",
                                        dataScope.userField(),
                                        loginUser.getUserId()
                                ));
                            } else {
                                sqlBuilder.append(StrUtil.format(
                                        " OR {}.{} = {} ",
                                        dataScope.userAlias(),
                                        dataScope.userField(),
                                        loginUser.getUserId()
                                ));
                            }
                        } else {
                            log.warn("dataScope.userField() 未设置 userField");
                            continue;
                        }
                    } else {
                        log.warn("dataScope.userAlias() 未设置 userAlias");
                        continue;
                    }
                    break;
                default:
                    log.warn("未受支持的数据范围类型: {}", scopeType.getDesc());
                    break;
            }
            conditions.add(scope);
        }

        if (CollUtil.isEmpty(conditions)) {
            // 如果该用户拥有的角色都没有设置数据范围，不返回任何数据
            if (dataScope.isSingleQuery()) {
                sqlBuilder = new StringBuilder(" OR id = 0 ");
            } else if (StrUtil.isNotBlank(dataScope.deptAlias())) {
                sqlBuilder = new StringBuilder(StrUtil.format(" OR {}.id = 0 ", dataScope.deptAlias()));
            } else {
                log.warn("dataScope.deptAlias() 未设置 deptAlias");
            }
        }

        if (StrUtil.isNotEmpty(sqlBuilder)) {
            // 暂存拼接的SQL，然后在Mybatis执行SQL时，在自定义的数据权限拦截器中取出来使用
            // substring 4 是为了去掉开头的 OR
            String sql = StrUtil.format(" ({}) ", sqlBuilder.substring(4));
//            String sql = StrUtil.format(" AND ({}) ", sqlBuilder.substring(4));
            DataScopeContextHelper.set(sql);
        }
    }

    @After("@annotation(dataScope)")
    public void doAfter(JoinPoint joinPoint, DataScope dataScope) throws Throwable {
        // 对@DataScope注解的方法执行完成后执行，清除SQL
        log.debug("after dataScope: {}/{}", dataScope.deptAlias(), dataScope.userAlias());
        DataScopeContextHelper.clear();
    }

}
