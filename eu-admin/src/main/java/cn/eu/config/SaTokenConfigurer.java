package cn.eu.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.oss.properties.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaoeryu
 * @since 2023/6/1
 */
@Slf4j
@Configuration
public class SaTokenConfigurer implements WebMvcConfigurer {

    @Autowired
    OssProperties ossProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handler -> {
            SaRouter.match("/**")
                    .notMatch(
                            "/doc.html",
                            "/v3/api-docs/swagger-config",
                            "/v3/api-docs",
                            "/webjars/**",
                            "/favicon.ico",
                            "/swagger-ui/**",
                            "/swagger-resources/**",
                            ossProperties.getLocalFilePrefix() + "/**"
                    )
                    .check(r -> StpUtil.checkLogin());
        }))
        .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fileLocation = "file:" + ossProperties.getLocalPath().replace("\\","/");
        registry.addResourceHandler(ossProperties.getLocalFilePrefix() + "/**")
                .addResourceLocations(fileLocation)
                .setCachePeriod(0);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
