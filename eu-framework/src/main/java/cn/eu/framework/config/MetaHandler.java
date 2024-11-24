package cn.eu.framework.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 处理新增和更新的数据填充
 * <a href="https://baomidou.com/pages/4c6bcf/">参考</a>
 * @author zhaoey
 */
@Slf4j
@Component
public class MetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("start insert fill ....");
        try {
            String userId = Optional.ofNullable(StpUtil.getLoginIdDefaultNull()).map(String::valueOf).orElse(null);
            this.strictInsertFill(metaObject, "createBy", String.class, userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        try {
            String userId = Optional.ofNullable(StpUtil.getLoginIdDefaultNull()).map(String::valueOf).orElse(null);
            this.strictUpdateFill(metaObject, "updateBy", String.class, userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
