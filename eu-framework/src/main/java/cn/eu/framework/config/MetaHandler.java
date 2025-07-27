package cn.eu.framework.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.utils.LoginUtil;
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
        try {
            String loginId = LoginUtil.getLoginId();
            this.strictInsertFill(metaObject, "createBy", String.class, loginId);
        } catch (Exception e) {
            log.error("填充createBy异常 {}", e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            String loginId = LoginUtil.getLoginId();
            this.strictUpdateFill(metaObject, "updateBy", String.class, loginId);
        } catch (Exception e) {
            log.error("填充updateBy异常 {}", e.getMessage());
        }
    }

}
