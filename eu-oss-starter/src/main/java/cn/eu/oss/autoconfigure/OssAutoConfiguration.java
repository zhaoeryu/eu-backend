package cn.eu.oss.autoconfigure;

import cn.eu.oss.properties.OssProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Configuration
@EnableConfigurationProperties({ OssProperties.class })
public class OssAutoConfiguration {



}
