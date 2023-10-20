package cn.eu.autoconfigure;

import cn.eu.common.filter.XssFilter;
import cn.eu.common.utils.SpringContextHolder;
import cn.eu.properties.EuProperties;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoeryu
 * @since 2023/6/11
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({EuProperties.class})
public class EuAutoConfiguration {

    @Bean
    @ConditionalOnProperty(
        value = "eu.xss.enabled",
        havingValue = "true",
        matchIfMissing = true
    )
    public FilterRegistrationBean<XssFilter> xssFilterRegistration(EuProperties euProperties) {
        log.info("注入 XssFilter");
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(StrUtil.splitToArray(euProperties.getXss().getUrlPatterns(), ","));
        registration.setName("xssFilter");
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes" , StrUtil.blankToDefault(euProperties.getXss().getExcludes(), ""));
        registration.setInitParameters(initParameters);
        return registration;
    }

    /**
     * Spring上下文工具配置
     */
    @Bean
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        log.info("注入 SpringContextHolder");
        return new SpringContextHolder();
    }

    /**
     * Knife4j配置
     */
    @Bean
    public OpenAPI api(EuProperties euProperties) {
        Info info = euProperties.getKnife4j().getInfo();
        return new OpenAPI().info(new Info()
            // 标题
            .title(info.getTitle())
            // 描述
            .description(info.getDescription())
            // 联系人
            .contact(info.getContact())
            // API服务条款
            .termsOfService(info.getTermsOfService())
            // 版本
            .version(info.getVersion())
        );
    }
}
