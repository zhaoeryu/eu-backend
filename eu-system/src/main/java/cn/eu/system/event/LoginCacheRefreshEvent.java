package cn.eu.system.event;

import org.springframework.context.ApplicationEvent;

/**
 * 刷新登录缓存事件
 * @author zhaoeryu
 * @since 2023/8/8
 */
public class LoginCacheRefreshEvent extends ApplicationEvent {

    public LoginCacheRefreshEvent(Object source) {
        super(source);
    }
}
