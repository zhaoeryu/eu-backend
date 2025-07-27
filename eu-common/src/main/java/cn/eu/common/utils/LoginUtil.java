package cn.eu.common.utils;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.constants.Constants;
import cn.eu.common.model.LoginUser;
import cn.eu.common.model.dto.RoleDto;
import cn.eu.common.security.LoginContextHolder;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Eu.z
 * @since 2024/7/14
 */
@Slf4j
public class LoginUtil {

    /**
     * 获取当前登录用户ID
     */
    public static String getLoginId() {
        String val = LoginContextHolder.get();
        if (val != null) {
            return val;
        }
        Object stpVal = StpUtil.getLoginIdDefaultNull();
        if (stpVal != null) {
            return String.valueOf(stpVal);
        }
        return null;
    }

    /**
     * 当前登录用户是否为管理员
     */
    public static boolean isAdminLogin() {
        return Optional.ofNullable(getLoginUser())
                .map(user -> user.getAdmin() == 1)
                .orElse(false);
    }

    /**
     * 获取当前登录用户
     */
    public static LoginUser getLoginUser() {
        SaSession session = getSession();
        if (session == null) {
            return null;
        }
        return getLoginUserBySaSession(session);
    }

    public static SaSession getSession() {
        String loginId = getLoginId();
        if (loginId == null) {
            return null;
        }
        return StpUtil.getSessionByLoginId(loginId);
    }

    /**
     * 从SaSession中提取登录用户数据
     * @param session SaSession
     * @return AuthUser
     */
    public static LoginUser getLoginUserBySaSession(SaSession session) {
        if (session == null) {
            return null;
        }
        Object userStr = session.get(Constants.USER_KEY);
        if (userStr == null) {
            return null;
        }
        if (userStr instanceof LoginUser) {
            return (LoginUser) userStr;
        }
        return JSONObject.parseObject(userStr.toString(), LoginUser.class);
    }

    public static String getNickname() {
        return Optional.ofNullable(getLoginUser())
                .map(LoginUser::getNickname)
                .orElse(null);
    }

    /**
     * 设置当前登录用户
     */
    public static void setLoginUser(LoginUser authUser) {
        Optional.ofNullable(getSession())
                .ifPresent(session -> {
                    session.set(Constants.USER_KEY, authUser);
                });
    }

    /**
     * 设置当前登录用户拥有的角色
     */
    public static void setLoginUserRoles(List<RoleDto> roles) {
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        Optional.ofNullable(getSession())
                .ifPresent(session -> {
                    session.set(Constants.ROLE_KEY, roles);
                });
    }

    public static List<RoleDto> getLoginUserRoles() {
        SaSession session = getSession();
        if (session == null) {
            return new ArrayList<>();
        }
        Object roles = session.get(Constants.ROLE_KEY);
        List<RoleDto> roleList = new ArrayList<>();
        try {
            roleList = JSONArray.parseArray(roles.toString(), RoleDto.class);
        } catch (Exception e) {
            log.error("getLoginUserRoles error", e);
        }
        return roleList;
    }
}
