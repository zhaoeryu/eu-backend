package cn.eu.security;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.constants.Constants;
import cn.eu.common.model.AuthUser;
import cn.eu.system.domain.SysRole;
import cn.eu.system.domain.SysUser;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
@Slf4j
public class SecurityUtil {

    /**
     * 当前登录用户是否为管理员
     */
    public static boolean isAdminLogin() {
        Object extra = StpUtil.getSession().get(Constants.IS_ADMIN_KEY);
        return extra != null && StrUtil.equals(extra.toString(), "1");
    }

    /**
     * 获取当前登录用户
     */
    public static AuthUser getLoginUser() {
        Object userStr = StpUtil.getSession().get(Constants.USER_KEY);
        Objects.requireNonNull(userStr);
        return JSONObject.parseObject(userStr.toString(), AuthUser.class);
    }

    /**
     * 从SaSession中提取登录用户数据
     * @param session SaSession
     * @return AuthUser
     */
    public static AuthUser getLoginUserBySaSession(SaSession session) {
        Object userStr = session.get(Constants.USER_KEY);
        Objects.requireNonNull(userStr);
        return JSONObject.parseObject(userStr.toString(), AuthUser.class);
    }

    /**
     * 设置当前登录用户
     */
    public static void setLoginUser(AuthUser authUser) {
        StpUtil.getSession().set(Constants.USER_KEY, authUser);
    }

    /**
     * 设置当前登录用户是否为管理员
     */
    public static void setLoginUserIsAdmin(Integer admin) {
        StpUtil.getSession().set(Constants.IS_ADMIN_KEY, admin);
    }

    /**
     * 设置当前登录用户拥有的角色
     */
    public static void setLoginUserRoles(List<SysRole> roles) {
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        StpUtil.getSession().set(Constants.ROLE_KEY, roles);
    }

    public static List<SysRole> getLoginUserRoles() {
        Object roles = StpUtil.getSession().get(Constants.ROLE_KEY);
        List<SysRole> roleList = new ArrayList<>();
        try {
            roleList = Optional.ofNullable(JSONObject.parseArray(roles.toString(), SysRole.class))
                    .orElse(new ArrayList<>());
        } catch (Exception e) {
            log.error("getLoginUserRoles error", e);
        }
        return roleList;
    }

    /**
     * 根据SysUser填充AuthUser
     */
    public static AuthUser fillAuthUserBySysUser(AuthUser authUser, SysUser sysUser) {
        if (StrUtil.isNotBlank(sysUser.getId())) {
            authUser.setUserId(sysUser.getId());
        }
        if (StrUtil.isNotBlank(sysUser.getUsername())) {
            authUser.setUsername(sysUser.getUsername());
        }
        if (StrUtil.isNotBlank(sysUser.getNickname())) {
            authUser.setNickname(sysUser.getNickname());
        }
        if (StrUtil.isNotBlank(sysUser.getAvatar())) {
            authUser.setAvatar(sysUser.getAvatar());
        }
        if (StrUtil.isNotBlank(sysUser.getMobile())) {
            authUser.setMobile(sysUser.getMobile());
        }
        if (StrUtil.isNotBlank(sysUser.getEmail())) {
            authUser.setEmail(sysUser.getEmail());
        }
        if (sysUser.getSex() != null) {
            authUser.setSex(sysUser.getSex());
        }
        if (sysUser.getAdmin() != null) {
            authUser.setAdmin(sysUser.getAdmin());
        }
        if (sysUser.getDeptId() != null) {
            authUser.setDeptId(sysUser.getDeptId());
        }
        return authUser;
    }
}
