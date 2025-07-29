package cn.eu.common.enums;

import cn.eu.common.annotation.IEuEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统用户状态
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Getter
@AllArgsConstructor
public enum SysUserStatus implements IEuEnum<Integer> {

    /**
     * 正常
     */
    NORMAL(0, "正常"),

    /**
     * 禁用
     */
    DISABLE(1, "禁用"),

    /**
     * 删除
     */
    DELETED(2, "删除");

    @JsonValue
    private final Integer value;
    private final String desc;

    /**
     * 账号是否正常
     */
    public static boolean isNormal(SysUserStatus value) {
        return NORMAL == value;
    }
}
