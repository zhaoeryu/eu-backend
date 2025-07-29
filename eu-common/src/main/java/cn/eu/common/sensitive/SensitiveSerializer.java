package cn.eu.common.sensitive;

import cn.eu.common.annotation.Sensitive;
import cn.eu.common.core.service.SensitiveService;
import cn.eu.common.utils.SpringContextHolder;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * 数据脱敏json序列化工具
 *
 * @author Eu.z
 * @since 2025/7/29
 */
@Slf4j
@NoArgsConstructor
public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Sensitive sensitive;

    public SensitiveSerializer(final Sensitive sensitive) {
        this.sensitive = sensitive;
    }

    @SneakyThrows
    private String handler(Sensitive sensitive, String value) {
        if (sensitive == null) {
            return value;
        }

        try {
            // 检查是否需要脱敏
            SensitiveService sensitiveService = SpringContextHolder.getBean(SensitiveService.class);
            if (ObjectUtil.isNotNull(sensitiveService) && !sensitiveService.isSensitive()) {
                return value;
            }

            // 脱敏处理
            final Sensitive.Type type = sensitive.type();
            switch (type) {
                case STRATEGY:
                    SensitiveStrategy instance = sensitive.strategy().getDeclaredConstructor().newInstance();
                    return instance.sensitive(value);
                case CUSTOM:
                    return StrUtil.hide(value, sensitive.startInclude(), sensitive.endInclude());
                case CHINESE_NAME:
                    return DesensitizedUtil.chineseName(value);
                case ID_CARD:
                    return DesensitizedUtil.idCardNum(value, 1, 2);
                case FIXED_PHONE:
                    return DesensitizedUtil.fixedPhone(value);
                case MOBILE_PHONE:
                    return DesensitizedUtil.mobilePhone(value);
                case ADDRESS:
                    return DesensitizedUtil.address(value, 6);
                case EMAIL:
                    return DesensitizedUtil.email(value);
                case PASSWORD:
                    return DesensitizedUtil.password(value);
                case CAR_LICENSE:
                    return DesensitizedUtil.carLicense(value);
                case BANK_CARD:
                    return DesensitizedUtil.bankCard(value);
                case IPV4:
                    return DesensitizedUtil.ipv4(value);
                case IPV6:
                    return DesensitizedUtil.ipv6(value);
                case FIRST_MASK:
                    return DesensitizedUtil.firstMask(value);
                default:
                    return value;
            }
        } catch (Exception ex) {
            log.error("脱敏异常", ex);
        }
        return value;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(handler(sensitive, value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Sensitive annotation = property.getAnnotation(Sensitive.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {
            return new SensitiveSerializer(annotation);
        }
        return prov.findValueSerializer(property.getType(), property);
    }
}