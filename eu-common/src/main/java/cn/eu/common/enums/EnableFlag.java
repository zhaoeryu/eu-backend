package cn.eu.common.enums;

import cn.eu.common.annotation.IEuEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Eu.z
 * @since 2025/7/29
 */
@Getter
@AllArgsConstructor
public enum EnableFlag implements IEuEnum<Integer> {

    ENABLED(0, "启用"),
    DISABLED(1, "禁用");

    @JsonValue
    private final Integer value;
    private final String desc;

}
