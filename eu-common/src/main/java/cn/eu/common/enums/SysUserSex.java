package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/7/31
 */
@Getter
@AllArgsConstructor
public enum SysUserSex {

    /**
     * 女
     */
    WOMAN(0, "女"),
    /**
     * 男
     */
    MAN(1, "男");

    private final int value;
    private final String desc;

    public static String parseValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (SysUserSex item : SysUserSex.values()) {
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
        for (SysUserSex item : SysUserSex.values()) {
            if (item.desc.equals(desc)) {
                return item.getValue();
            }
        }
        return null;
    }
}
