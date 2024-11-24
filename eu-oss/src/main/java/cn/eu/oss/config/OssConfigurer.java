package cn.eu.oss.config;

import cn.eu.oss.properties.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaoeryu
 * @since 2023/7/1
 */
@Configuration
@EnableConfigurationProperties({ OssProperties.class })
public class OssConfigurer implements WebMvcConfigurer {

    @Autowired
    OssProperties ossProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fileLocation = "file:" + ossProperties.getLocalPath().replace("\\","/");
        registry.addResourceHandler(ossProperties.getLocalFilePrefix() + "/**")
                .addResourceLocations(fileLocation)
                .setCachePeriod(0);
    }

}
