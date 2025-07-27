package cn.eu.framework.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.interceptor.LoginInfoInterceptor;
import cn.eu.common.properties.EuProperties;
import cn.eu.common.utils.ServiceLoadUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ServiceLoaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/1
 */
@Slf4j
@Configuration
public class SaTokenConfigurer implements WebMvcConfigurer {

    @Autowired
    EuProperties euProperties;

    private static final List<String> DEFAULT_EXCLUDE_URIS = Arrays.asList(
            "/doc.html",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs",
            "/webjars/**",
            "/favicon.ico",
            "/swagger-ui/**",
            "/swagger-resources/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        List<String> excludeUriPatterns = new ArrayList<>();
        excludeUriPatterns.addAll(DEFAULT_EXCLUDE_URIS);
        excludeUriPatterns.addAll(euProperties.getSecurity().getExcludeUriPatterns());
        excludeUriPatterns.addAll(ServiceLoadUtils.loadSecurityExcludeUriPatterns());
        registry.addInterceptor(new SaInterceptor(handler -> {
            SaRouter.match("/**")
                .notMatch(excludeUriPatterns)
                .check(r -> StpUtil.checkLogin());
        }))
        .addPathPatterns("/**");
        registry.addInterceptor(new LoginInfoInterceptor())
                .addPathPatterns("/**");
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
