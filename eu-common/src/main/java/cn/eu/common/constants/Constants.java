package cn.eu.common.constants;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
public class Constants {

    /** 当前登录账号信息的字段KEY */
    public static final String USER_KEY = "user";
    /** 当前登录账号的角色列表字段KEY */
    public static final String ROLE_KEY = "role";
    /** 默认管理员角色字符串 */
    public static final String ADMIN_ROLE = "*";
    /** 默认管理员权限字符串 */
    public static final String ADMIN_PERMISSION = "*";


    /** 验证码RedisKey */
    public static final String CAPTCHA_REDIS_KEY = "captcha:";
    /** 验证码有效期, 单位：秒 */
    public static final int CAPTCHA_EXPIRATION = 60 * 5;

    /** 尝试登录的redisKey */
    public static final String TRY_LOGIN_COUNT_REDIS_KEY = "login:try:";
    /** 最大尝试登录的次数 */
    public static final int MAX_TRY_LOGIN_LIMIT = 5;
    /**
     * 10分钟内超过最大尝试登录次数则锁定账号, 单位：秒
     */
    public static final int TRY_LOGIN_CACHE_TIME = 60 * 10;
    /**
     * 超过最大尝试登录后的账号锁定RedisKey
     */
    public static final String LOCK_LOGIN_REDIS_KEY = "login:lock:";
    /**
     * 超过最大尝试登录后的账号锁定时间, 单位：秒
     */
    public static final int LOCK_TIME = 60 * 60 * 3;


    /** 用户默认密码 */
    public static final String DEFAULT_PASSWORD = "123123";

    /**
     * 逻辑删除标记 - 正常
     */
    public static final Integer DEL_FLAG_NORMAL = 0;
    /**
     * 逻辑删除标记 - 删除
     */
    public static final Integer DEL_FLAG_DELETE = 1;
    /**
     * 密码字段名
     */
    public static final String PASSWORD_FIELD_NAME = "password";
    /**
     * 逻辑删除字段
     */
    public static final String DEL_FLAG_FIELD_NAME = "delFlag";

    /**
     * 前端请求头 - 前端标识
     */
    public static final String REQUEST_HEADER_FRONT_KEY = "X-Eu-Front";
    /**
     * 前端请求头 - 版本标识
     */
    public static final String REQUEST_HEADER_FRONT_VERSION_KEY = "X-Eu-Front-Version";

    /**
     * WebSocket Session Token Key
     */
    public static final String WS_SESSION_TOKEN_KEY = "token";
    /**
     * WebSocket Session User Id Key
     */
    public static final String WS_SESSION_USER_ID_KEY = "userId";
    /**
     * WebSocket Session User Id Key
     */
    public static final String WS_SESSION_CMD_KEY = "cmd";

    /**
     * 防止重复提交RedisKey
     */
    public static final String REPEAT_SUBMIT_REDIS_KEY = "repeat_submit:";
}
