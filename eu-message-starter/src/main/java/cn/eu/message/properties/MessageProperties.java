package cn.eu.message.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Data
@ConfigurationProperties(prefix = "eu.message")
public class MessageProperties {

}
