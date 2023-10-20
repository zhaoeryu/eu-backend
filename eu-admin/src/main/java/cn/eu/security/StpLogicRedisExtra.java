package cn.eu.security;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpLogic;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 扩展Sa-Token的StpLogic 支持extra数据存储到redis
 * @author zhaoeryu
 * @since 2023/6/6
 */
@Slf4j
public class StpLogicRedisExtra extends StpLogic {
    public StpLogicRedisExtra() {
        super("login");
    }
    public StpLogicRedisExtra(String loginType) {
        super(loginType);
    }

    @Override
    public String createLoginSession(Object id, SaLoginModel loginModel) {
        String token = super.createLoginSession(id, loginModel);

        // 保存额外信息
        if (loginModel.isSetExtraData()) {
            // 如果设置了额外信息，则保存额外信息
            getSaTokenDao().setObject(splicingKeyExtra(token), loginModel.getExtraData(), loginModel.getTimeout());
        }

        return token;
    }

    @Override
    public Object getExtra(String key) {
        return getExtra(getTokenValue(), key);
    }

    @Override
    public Object getExtra(String tokenValue, String key) {
        log.debug("getExtra:{},{}", tokenValue, key);
        Object object = getSaTokenDao().getObject(splicingKeyExtra(tokenValue));
        if (object == null) {
            return null;
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(object.toString());
            return jsonObject.get(key);
        } catch (Exception e) {
            log.error("getExtra error", e);
        }
        return null;
    }

    @Override
    public void logoutByTokenValue(String tokenValue) {
        log.debug("logout: {}，准备清除extra", tokenValue);
        super.logoutByTokenValue(tokenValue);

        // 清除扩展数据
        getSaTokenDao().delete(splicingKeyExtra(tokenValue));
    }

    private String splicingKeyExtra(String token) {
        return getConfig().getTokenName() + ":" + this.loginType + ":extra:" + token;
    }
}
