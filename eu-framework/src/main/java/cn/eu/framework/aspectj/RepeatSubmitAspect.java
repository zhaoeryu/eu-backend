package cn.eu.framework.aspectj;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.eu.common.annotation.RepeatSubmit;
import cn.eu.common.constants.Constants;
import cn.eu.common.exception.ServiceException;
import cn.eu.common.utils.MessageUtils;
import cn.eu.common.utils.RedisUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 防止重复提交
 *
 * @author Eu.z
 * @since 2024/11/25
 */
@Slf4j
@Aspect
@Component
public class RepeatSubmitAspect {

    @Autowired
    RedisUtil redisUtil;

    @Before("@annotation(repeatSubmit)")
    public void doBefore(JoinPoint joinPoint, RepeatSubmit repeatSubmit) {
        long interval = 0;
        if (repeatSubmit.interval() > 0) {
            interval = repeatSubmit.unit().toMillis(repeatSubmit.interval());
        }
        if (interval < 1000) {
            throw new ServiceException(MessageUtils.parseMessage("{error.repeat.submit.interval_error}", 1));
        }

        String cacheKey = buildCacheKey(joinPoint.getArgs());
        Boolean hasKey = redisUtil.hasKey(cacheKey);
        if (hasKey) {
            throw new ServiceException(MessageUtils.parseMessage(repeatSubmit.message()));
        }

        redisUtil.setEx(cacheKey, "", interval, TimeUnit.MILLISECONDS);
    }

    /**
     * 构建缓存Key,格式：prefix + uri + md5(token + params)
     * @param args controller方法参数
     * @return 缓存Key
     */
    private String buildCacheKey(Object[] args) {
        HttpServletRequest request = SpringMVCUtil.getRequest();
        String requestURI = request.getRequestURI();
        String token = StrUtil.trim(request.getHeader(SaManager.getConfig().getTokenName()));
        String params = marshalParams(args);
        String submitKey = SecureUtil.md5(token + params);
        return Constants.REPEAT_SUBMIT_REDIS_KEY + requestURI + submitKey;
    }

    private String marshalParams(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (shouldIgnore(arg)) {
                continue;
            }
            try {
                sb.append(JSONObject.toJSONString(arg));
            } catch (Exception e) {
                log.error("在处理防止重复提交参数时发生异常", e);
            }
        }
        return sb.toString();
    }

    /**
     * 判断参数是否应该被忽略
     */
    private boolean shouldIgnore(Object arg) {
        if (arg == null) {
            return true;
        }

        Class<?> clazz = arg.getClass();

        // 检查数组类型
        if (clazz.isArray()) {
            return MultipartFile.class.isAssignableFrom(clazz.getComponentType());
        }

        // 检查 Collection 类型
        if (Collection.class.isAssignableFrom(clazz)) {
            Collection<?> collection = (Collection<?>) arg;
            for (Object item : collection) {
                if (item instanceof MultipartFile) {
                    return true;
                }
            }
            return false;
        }

        // 检查 Map 类型
        if (Map.class.isAssignableFrom(clazz)) {
            Map<?, ?> map = (Map<?, ?>) arg;
            for (Object value : map.values()) {
                if (value instanceof MultipartFile) {
                    return true;
                }
            }
            return false;
        }

        // 检查单个对象是否为 MultipartFile 或其他需要忽略的类型
        return arg instanceof MultipartFile ||
                arg instanceof HttpServletRequest ||
                arg instanceof HttpServletResponse ||
                arg instanceof BindingResult;
    }
}
