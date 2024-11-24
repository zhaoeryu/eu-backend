package cn.eu.framework.config;

import cn.eu.common.interceptor.DataPermissionInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/8/8
 */
@Slf4j
@Component
public class EuApplicationRunner implements ApplicationRunner {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(dataPermissionInterceptor);
        }
        log.info("注入 DataPermissionInterceptor");
    }
}
