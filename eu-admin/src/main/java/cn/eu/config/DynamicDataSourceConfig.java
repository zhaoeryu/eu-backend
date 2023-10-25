package cn.eu.config;

import cn.eu.common.constants.Constants;
import cn.eu.common.datasource.DSConstants;
import cn.eu.common.datasource.DynamicDataSourceContextHolder;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.servlet.*;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoeryu
 * @since 2023/10/24
 */
@Slf4j
@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ConditionalOnProperty(prefix = Constants.DS_BASE_PREFIX, name = "enabled", havingValue = "true")
public class DynamicDataSourceConfig implements ApplicationContextAware {

    private static final String DS_BEAN_SUFFIX = "DataSource";

    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @Bean(DSConstants.MASTER + DS_BEAN_SUFFIX)
    public DataSource masterDataSource() {
        return druidDataSourceWrapper(DruidDataSourceBuilder.create().build(), Constants.DS_PREFIX + DSConstants.MASTER);
    }

    @Bean(DSConstants.SLAVE + DS_BEAN_SUFFIX)
    @ConditionalOnProperty(prefix = Constants.DS_PREFIX + DSConstants.SLAVE, name = "url")
    public DataSource slaveDataSource() {
        return druidDataSourceWrapper(DruidDataSourceBuilder.create().build(), Constants.DS_PREFIX + DSConstants.SLAVE);
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource(DataSource masterDataSource) {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DSConstants.MASTER, masterDataSource);
        // 添加其他数据源
        addDataSource(dataSourceMap, DSConstants.SLAVE);

        // 设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);

        log.info("动态数据源装配完成");
        return dynamicDataSource;
    }

    private void addDataSource(Map<Object, Object> dataSourceMap, String env) {
        String beanName = env + DS_BEAN_SUFFIX;
        boolean isExist = applicationContext.containsBean(beanName);
        if (!isExist) {
            return;
        }
        DataSource ds = applicationContext.getBean(beanName, DataSource.class);
        dataSourceMap.put(env, ds);
    }

    private DataSource druidDataSourceWrapper(DruidDataSource dataSource, String prefix) {
        log.info("装配数据源：{}", prefix);
        if (dataSource.getUsername() == null) {
            dataSource.setUsername(environment.getProperty(prefix + ".username"));
        }
        if (dataSource.getPassword() == null) {
            dataSource.setPassword(environment.getProperty(prefix + ".password"));
        }
        if (dataSource.getUrl() == null) {
            dataSource.setUrl(environment.getProperty(prefix + ".url"));
        }
        if (dataSource.getDriverClassName() == null) {
            dataSource.setDriverClassName(environment.getProperty(prefix + ".driver-class-name"));
        }
        return dataSource;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    static class DynamicDataSource  extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DynamicDataSourceContextHolder.getContextKey();
        }
    }

    /**
     * 去除监控页面底部的广告
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true")
    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        // 创建filter进行过滤
        Filter filter = (request, response, chain) -> {
            chain.doFilter(request, response);
            // 重置缓冲区，响应头不会被重置
            response.resetBuffer();
            // 获取common.js
            String text = Utils.readFromResource(filePath);
            // 移除底部的广告信息
            text = text.replaceAll("<footer[^>]*>[\\s\\S]*?</footer>", "");
            response.getWriter().write(text);
        };
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
