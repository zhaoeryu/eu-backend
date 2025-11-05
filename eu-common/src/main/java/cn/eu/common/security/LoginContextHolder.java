package cn.eu.common.security;

import cn.eu.common.model.LoginUser;

import java.util.Optional;

/**
 * 用于存储登录信息
 * @author Eu.z
 * @since 2025/7/27
 */
public class LoginContextHolder {

    private static final ThreadLocal<LoginUser> userHolder = new ThreadLocal<>();

    public static Optional<LoginUser> get() {
        return Optional.ofNullable(userHolder.get());
    }

    public static void set(LoginUser val) {
        if (val != null) {
            userHolder.set(val);
        }
    }

    public static void reset() {
        userHolder.remove();
    }

}
