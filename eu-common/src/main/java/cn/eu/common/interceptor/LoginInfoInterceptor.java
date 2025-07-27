package cn.eu.common.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.security.LoginContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器 - 用于获取登录信息并存入 ThreadLocal
 * @author Eu.z
 * @since 2025/7/27
 */
@Component
public class LoginInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Object loginIdDefaultNull = StpUtil.getLoginIdDefaultNull();
        if (loginIdDefaultNull != null) {
            LoginContextHolder.set(String.valueOf(loginIdDefaultNull));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        // 清理 ThreadLocal，防止内存泄漏
        LoginContextHolder.reset();
    }
}