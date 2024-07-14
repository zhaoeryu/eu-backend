package cn.eu.security;

import cn.dev33.satoken.stp.StpInterface;
import cn.eu.common.constants.Constants;
import cn.eu.common.utils.LoginUtil;
import cn.eu.system.service.ISysMenuService;
import cn.eu.system.service.ISysRoleService;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/1
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    ISysRoleService sysRoleService;
    @Autowired
    ISysMenuService sysMenuService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.debug("getPermissionList {} {}", loginId, loginType);
        // 判断是否为系统管理员
        if (LoginUtil.isAdminLogin()) {
            // 系统管理员拥有所有权限
            return CollUtil.newArrayList(Constants.ADMIN_PERMISSION);
        }
        return sysMenuService.getMenuPermissionByUserId(loginId.toString());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.debug("getRoleList {} {}", loginId, loginType);
        if (LoginUtil.isAdminLogin()) {
            return CollUtil.newArrayList(Constants.ADMIN_ROLE);
        }
        return sysRoleService.getRolePermissionByUserId(loginId.toString());
    }

}
