package cn.eu.common.exception;

import cn.eu.common.utils.MessageUtils;

/**
 * 将所有业务错误码放在一个枚举里，方便管理
 * 可以按模块划分，比如用户模块、订单模块等
 * @author Eu.z
 * @since 2026/3/12
 */
public enum EuErrorCode implements ErrorCode {

    // 成功
    SUCCESS(200, "success"),

    // 通用错误 (10000 - 19999)
    PARAMS_VALID_ERROR(10001, MessageUtils.message("errorCode.common.paramsValidError")),
    PARAMS_CONVERT_ERROR(10002, MessageUtils.message("errorCode.common.paramsConvertError")),
    PARAMS_MISSING_ERROR(10003, MessageUtils.message("errorCode.common.paramsMissingError")),
    PARAMS_ILLEGAL_ERROR(10004, MessageUtils.message("errorCode.common.paramsIllegalError")),
    DUPLICATE_KEY_ERROR(10005, MessageUtils.message("errorCode.common.duplicateKeyError")),
    REQUEST_FORMAT_ERROR(10006, MessageUtils.message("errorCode.common.requestFormatError")),
    RESOURCE_NOT_FOUND(10010, MessageUtils.message("errorCode.common.resourceNotFound")),
    SYSTEM_BUSY(10998, MessageUtils.message("errorCode.common.systemBusy")),
    SERVER_ERROR(10999, MessageUtils.message("errorCode.common.serverError")),
    REPEAT_SUBMIT(11000, MessageUtils.message("errorCode.common.repeatSubmit")),

    // 系统模块错误(20000 ~ 29999)
    SYS_NOT_LOGIN(20000, MessageUtils.message("errorCode.system.notLogin")),
    SYS_NOT_ROLE(20001, MessageUtils.message("errorCode.system.noRole")),
    SYS_NOT_PERMISSION(20002, MessageUtils.message("errorCode.system.noPermission")),
    SYS_LOGIN_LOCK(20003, MessageUtils.message("errorCode.system.login.lockTpl")),
    SYS_LOGIN_USERNAME_OR_PASSWORD_ERROR(20004, MessageUtils.message("errorCode.system.login.usernameOrPasswordError")),
    SYS_LOGIN_CAPTCHA_ERROR(20005, MessageUtils.message("errorCode.system.login.captcha.error")),
    SYS_LOGIN_CAPTCHA_EXPIRE(20006, MessageUtils.message("errorCode.system.login.captcha.expire")),
    SYS_LOGIN_ACCOUNT_DISABLED(20007, MessageUtils.message("errorCode.system.login.account.disabled")),
    SYS_LOGIN_ACCOUNT_DELETED(20008, MessageUtils.message("errorCode.system.login.account.deleted")),
    ;

    private final int code;
    private final String message;

    EuErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
