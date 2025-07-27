package cn.eu.common.security;

/**
 * 用于存储登录信息
 * @author Eu.z
 * @since 2025/7/27
 */
public class LoginContextHolder {

    private static final ThreadLocal<String> userIdHolder = new ThreadLocal<>();

    public static String get() {
        return userIdHolder.get();
    }

    public static void set(String val) {
        if (val != null) {
            userIdHolder.set(val);
        }
    }

    public static void reset() {
        userIdHolder.remove();
    }

}
