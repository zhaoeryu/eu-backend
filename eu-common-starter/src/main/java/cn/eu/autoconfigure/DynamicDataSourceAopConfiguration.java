package cn.eu.autoconfigure;

import cn.eu.common.constants.Constants;
import cn.eu.common.datasource.DS;
import cn.eu.common.datasource.aop.DynamicDataSourceAnnotationAdvisor;
import cn.eu.common.datasource.aop.DynamicDataSourceAnnotationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoeryu
 * @since 2023/10/25
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = Constants.DS_BASE_PREFIX, name = "enabled", havingValue = "true")
public class DynamicDataSourceAopConfiguration {

    @Bean
    public Advisor dynamicDatasourceAnnotationAdvisor() {
        DynamicDataSourceAnnotationInterceptor interceptor = new DynamicDataSourceAnnotationInterceptor();
        DynamicDataSourceAnnotationAdvisor advisor = new DynamicDataSourceAnnotationAdvisor(interceptor, DS.class);
        advisor.setOrder(Integer.MIN_VALUE);
        log.info("注入DynamicDataSourceAnnotationAdvisor");
        return advisor;
    }

}
