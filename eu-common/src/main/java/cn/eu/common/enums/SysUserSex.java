package cn.eu.common.enums;

import cn.eu.common.annotation.IEuEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/7/31
 */
@Getter
@AllArgsConstructor
public enum SysUserSex implements IEuEnum<Integer> {

    /**
     * 女
     */
    WOMAN(0, "女"),
    /**
     * 男
     */
    MAN(1, "男");

    private final Integer value;
    private final String desc;

}
