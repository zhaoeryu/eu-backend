package cn.eu.common.annotation;

import cn.eu.common.jackson.EuEnumJsonDeserializer;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Objects;

/**
 * 自定义枚举接口
 * @author Eu.z
 * @since 2025/7/29
 */
@JsonDeserialize(using = EuEnumJsonDeserializer.class)
public interface IEuEnum<T extends Serializable> extends IEnum<T> {

    /**
     * 描述信息
     */
    String getDesc();

    @JsonValue
    @Override
    T getValue();

    /**
     * 获取指定类型枚举映射
     *
     * @param enumClass 枚举类
     * @param type      类型
     * @param <E>       包装类
     * @return 枚举值
     */
    static <E extends IEuEnum<?>> E of(Class<E> enumClass, Object type) {
        if (enumClass == null || type == null) {
            return null;
        }
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            final Object value = e.getValue();
            if (Objects.equals(type, value)) {
                return e;
            }
        }
        return null;
    }
}
