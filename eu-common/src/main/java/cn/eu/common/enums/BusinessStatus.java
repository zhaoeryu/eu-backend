package cn.eu.common.enums;

import cn.eu.common.annotation.IEuEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作状态
 * @author zhaoeryu
 */
@Getter
@AllArgsConstructor
public enum BusinessStatus implements IEuEnum<Integer> {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 失败
     */
    FAIL(1, "失败");

    private final Integer value;
    private final String desc;

}
