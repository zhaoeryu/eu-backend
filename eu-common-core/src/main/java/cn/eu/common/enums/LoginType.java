package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Getter
@AllArgsConstructor
public enum LoginType {

    ADMIN("admin"),
    APP("app");

    private final String value;

}
