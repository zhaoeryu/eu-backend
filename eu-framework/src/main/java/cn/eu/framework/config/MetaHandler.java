package cn.eu.framework.config;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.eu.common.model.LoginUser;
import cn.eu.common.utils.LoginUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

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
            if (!SpringMVCUtil.isWeb()) {
                return;
            }
            LoginUser loginUser = LoginUtil.getLoginUser();
            if (loginUser == null) {
                return;
            }
            this.strictInsertFill(metaObject, "createBy", String.class, loginUser.getUserId());
            this.strictInsertFill(metaObject, "createByName", String.class, loginUser.getNickname());
        } catch (Exception e) {
            log.error("填充createBy异常 {}", e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (!SpringMVCUtil.isWeb()) {
                return;
            }
            LoginUser loginUser = LoginUtil.getLoginUser();
            if (loginUser == null) {
                return;
            }
            this.strictUpdateFill(metaObject, "updateBy", String.class, loginUser.getUserId());
            this.strictUpdateFill(metaObject, "updateByName", String.class, loginUser.getNickname());
        } catch (Exception e) {
            log.error("填充updateBy异常 {}", e.getMessage());
        }
    }

}
