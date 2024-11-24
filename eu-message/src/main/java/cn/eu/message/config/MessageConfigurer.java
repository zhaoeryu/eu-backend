package cn.eu.message.config;

import cn.eu.message.properties.MessageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
@Configuration
@EnableConfigurationProperties({MessageProperties.class})
public class MessageConfigurer {


}
