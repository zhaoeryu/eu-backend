package cn.eu.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/6
 */
@Getter
@AllArgsConstructor
public enum IError {

    SUCCESS(200, "success"),
    ERROR(500, "error"),
    WARN(600, "warn"),

    NOT_LOGIN(401, "未登录"),
    NOT_PERMISSION(403, "未授权"),

    ;


    private final int code;
    private final String message;

}
