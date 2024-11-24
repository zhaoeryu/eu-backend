package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统用户状态
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Getter
@AllArgsConstructor
public enum SysPostStatus {

    /**
     * 正常
     */
    NORMAL(0, "正常"),

    /**
     * 禁用
     */
    DISABLE(1, "禁用");

    private final int value;
    private final String desc;

    public static SysPostStatus valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (SysPostStatus status : SysPostStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

    public static String parseValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (SysPostStatus status : SysPostStatus.values()) {
            if (status.getValue() == value) {
                return status.getDesc();
            }
        }
        return null;
    }

    public static Integer valueOfDesc(String desc) {
        if (desc == null) {
            return null;
        }
        for (SysPostStatus status : SysPostStatus.values()) {
            if (status.desc.equals(desc)) {
                return status.getValue();
            }
        }
        return null;
    }

    /**
     * 账号是否正常
     */
    public static boolean isNormal(Integer value) {
        if (value == null) {
            return false;
        }
        return NORMAL.getValue() == value;
    }
}
