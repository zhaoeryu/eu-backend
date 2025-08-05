package cn.eu.common.enums;

import cn.eu.common.annotation.IEuEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Eu.z
 * @since 2025/7/29
 */
@Getter
@AllArgsConstructor
public enum BooleanFlag implements IEuEnum<Integer> {

    FALSE(0, "否"),
    TRUE(1, "是");

    private final Integer value;
    private final String desc;

}
