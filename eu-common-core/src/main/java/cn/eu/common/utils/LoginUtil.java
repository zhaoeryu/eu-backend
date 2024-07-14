package cn.eu.common.utils;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.constants.Constants;
import cn.eu.common.model.LoginUser;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Eu.z
 * @since 2024/7/14
 */
@Slf4j
public class LoginUtil {

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
        return getLoginUserBySaSession(StpUtil.getSession());
    }

    /**
     * 从SaSession中提取登录用户数据
     * @param session SaSession
     * @return AuthUser
     */
    public static LoginUser getLoginUserBySaSession(SaSession session) {
        Object userStr = session.get(Constants.USER_KEY);
        Objects.requireNonNull(userStr);
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
        StpUtil.getSession().set(Constants.USER_KEY, authUser);
    }

    /**
     * 设置当前登录用户拥有的角色
     */
    public static void setLoginUserRoles(List<?> roles) {
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        StpUtil.getSession().set(Constants.ROLE_KEY, roles);
    }

    public static <T> List<T> getLoginUserRoles(Class<T> clazz) {
        Object roles = StpUtil.getSession().get(Constants.ROLE_KEY);
        List<T> roleList = new ArrayList<>();
        try {
            roleList = Optional.ofNullable(JSONObject.parseArray(roles.toString(), clazz))
                    .orElse(new ArrayList<>());
        } catch (Exception e) {
            log.error("getLoginUserRoles error", e);
        }
        return roleList;
    }
}
