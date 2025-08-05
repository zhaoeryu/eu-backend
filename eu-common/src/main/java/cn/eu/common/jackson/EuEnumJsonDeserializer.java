package cn.eu.common.jackson;

import cn.eu.common.annotation.IEuEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Enum反序列化
 *
 * @author Eu.z
 * @since 2025/8/5
 */
@NoArgsConstructor
public class EuEnumJsonDeserializer extends JsonDeserializer<IEuEnum<?>> implements ContextualDeserializer {

    private Class<? extends IEuEnum<?>> propertyClass;
    private Class<?> valueType; // 新增：记录枚举值的实际类型

    public EuEnumJsonDeserializer(Class<? extends IEuEnum<?>> propertyClass, Class<?> valueType) {
        this.propertyClass = propertyClass;
        this.valueType = valueType;
    }

    @Override
    public IEuEnum<?> deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        if (propertyClass == null) {
            return null;
        }

        // 根据实际值类型解析JSON值
        Object value = parseValue(p, ctx, valueType);
        return IEuEnum.of(propertyClass, value);
    }

    /**
     * 根据目标类型解析JSON值
     */
    private Object parseValue(JsonParser p, DeserializationContext ctx, Class<?> valueType) throws IOException {
        if (p.getCurrentToken() == null) {
            return null;
        }

        // 根据JSON实际的token类型来解析值
        switch (p.getCurrentToken()) {
            case VALUE_STRING:
                return p.getValueAsString();
            case VALUE_NUMBER_INT:
                // 支持short、byte等小整数类型
                if (valueType != null) {
                    if (int.class == valueType || Integer.class == valueType) {
                        return p.getValueAsInt();
                    } else if (long.class == valueType || Long.class == valueType) {
                        return p.getValueAsLong();
                    } else if (short.class == valueType || Short.class == valueType) {
                        return (short)p.getValueAsInt();
                    } else if (byte.class == valueType || Byte.class == valueType) {
                        return (byte)p.getValueAsInt();
                    }
                }
                return p.getValueAsLong(); // 默认返回long
            case VALUE_NUMBER_FLOAT:
                // 支持BigDecimal等类型
                if (valueType != null) {
                    if (float.class == valueType || Float.class == valueType) {
                        return (float) p.getValueAsDouble();
                    } else if (double.class == valueType || Double.class == valueType) {
                        return p.getValueAsDouble();
                    } else if (BigDecimal.class == valueType) {
                        return p.getDecimalValue();
                    }
                }
                return p.getValueAsDouble();
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            case VALUE_NULL:
                return null;
            default:
                // 对于其他复杂类型，使用上下文转换
                if (valueType != null && valueType != Object.class) {
                    return ctx.readValue(p, valueType);
                }
                return p.getValueAsString();
        }
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctx, BeanProperty property) throws JsonMappingException {
        if (property == null) {
            return this;
        }

        // 获取枚举类型
        Class<?> rawClass = property.getType().getRawClass();
        // 增加类型检查，避免强制转换异常
        if (!IEuEnum.class.isAssignableFrom(rawClass)) {
            throw JsonMappingException.from(ctx, property.getName() + "的类型不是IEuEnum的实现类，无法使用EuEnumJsonDeserializer");
        }
        Class<? extends IEuEnum<?>> enumClass = (Class<? extends IEuEnum<?>>) rawClass;

        // 获取枚举值的实际类型（如Integer）
        Class<?> valueType = determineValueType(enumClass);

        return new EuEnumJsonDeserializer(enumClass, valueType);
    }

    /**
     * 确定枚举值的实际类型
     */
    private Class<?> determineValueType(Class<? extends IEuEnum<?>> enumClass) {
        // 通过反射获取枚举值的类型
        IEuEnum<?>[] enums = enumClass.getEnumConstants();
        if (enums != null && enums.length > 0) {
            return enums[0].getValue().getClass();
        }
        throw new IllegalArgumentException(String.format("无法确定枚举值的类型，请检查枚举类%s", enumClass.getName()));
    }
}