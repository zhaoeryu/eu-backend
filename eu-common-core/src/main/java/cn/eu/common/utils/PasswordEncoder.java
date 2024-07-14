package cn.eu.common.utils;

import cn.dev33.satoken.secure.BCrypt;

/**
 * @author zhaoeryu
 * @since 2023/6/6
 */
public class PasswordEncoder {

    /**
     * 密码加密
     * @param password 明文密码
     */
    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(5));
    }

    /**
     * 密码校验
     * @param password 明文密码
     * @param encodedPassword 加密后的密码
     */
    public static boolean matches(String password, String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(matches("admin123", encode("admin123")));
    }
}
