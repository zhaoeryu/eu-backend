package cn.eu.common.properties;

import io.swagger.v3.oas.models.info.Info;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * knife4j配置
 * @author zhaoeryu
 * @since 2023/7/12
 */
@Data
public class Knife4jProperties {

    @NestedConfigurationProperty
    private Info info = new Info();
}
