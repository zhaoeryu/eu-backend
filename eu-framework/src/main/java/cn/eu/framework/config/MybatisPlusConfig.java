package cn.eu.framework.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoey
 */
@Slf4j
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 防止全表更新与删除
//        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 数据权限
//        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
//        dataPermissionInterceptor.setDataPermissionHandler(new DataScopeDataPermissionHandler());
//        interceptor.addInnerInterceptor(dataPermissionInterceptor);
        return interceptor;
    }

}
