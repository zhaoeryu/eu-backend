package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作状态
 * @author zhaoeryu
 */
@Getter
@AllArgsConstructor
public enum BusinessStatus {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 失败
     */
    FAIL(1, "失败");


    private final int value;
    private final String desc;

    public static BusinessStatus valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (BusinessStatus status : BusinessStatus.values()) {
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
        for (BusinessStatus status : BusinessStatus.values()) {
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
        for (BusinessStatus status : BusinessStatus.values()) {
            if (status.desc.equals(desc)) {
                return status.getValue();
            }
        }
        return null;
    }
}
