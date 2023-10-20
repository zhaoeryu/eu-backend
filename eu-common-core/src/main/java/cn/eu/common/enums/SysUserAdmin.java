package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Getter
@AllArgsConstructor
public enum SysUserAdmin {

    /**
     * 普通用户
     */
    NORMAL(0, "普通用户"),

    /**
     * 管理员
     */
    ADMIN(1, "管理员");

    private final int value;
    private final String desc;

    public static String parseValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (SysUserAdmin item : SysUserAdmin.values()) {
            if (item.getValue() == value) {
                return item.getDesc();
            }
        }
        return null;
    }

    public static Integer valueOfDesc(String desc) {
        if (desc == null) {
            return null;
        }
        for (SysUserAdmin item : SysUserAdmin.values()) {
            if (item.desc.equals(desc)) {
                return item.getValue();
            }
        }
        return null;
    }
}
