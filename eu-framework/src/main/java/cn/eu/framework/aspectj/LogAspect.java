package cn.eu.framework.aspectj;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.eu.common.annotation.Log;
import cn.eu.common.enums.BusinessStatus;
import cn.eu.common.enums.BusinessType;
import cn.eu.common.model.LoginBody;
import cn.eu.common.utils.IpUtil;
import cn.eu.common.utils.LoginUtil;
import cn.eu.common.model.LoginUser;
import cn.eu.system.domain.SysOperLog;
import cn.eu.system.service.ISysOperLogService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.PropertyFilter;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author zhaoeryu
 */
@Aspect
@Component
@ConditionalOnProperty(
    value = "eu.save-log",
    havingValue = "true",
    matchIfMissing = true
)
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password" , "oldPassword" , "newPassword"};

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("_EU_OperationElapsedTime");

    @Autowired
    ISysOperLogService sysOperLogService;

    @PostConstruct
    public void init() {
        log.info("注入 LogAspect");
    }

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, Log controllerLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)" , returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)" , throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        try {
            // 获取当前的用户
            LoginUser authUser = null;
            if (StpUtil.isLogin()) {
                authUser = LoginUtil.getLoginUser();
            } else {
                // 未登录
                authUser = new LoginUser();
                if (controllerLog.businessType() == BusinessType.LOGIN) {
                    // 登录接口
                    if (CollUtil.isNotEmpty(Arrays.asList(joinPoint.getArgs()))) {
                        Object arg = joinPoint.getArgs()[0];
                        if (arg instanceof LoginBody) {
                            LoginBody loginBody = (LoginBody) arg;
                            authUser.setNickname(loginBody.getUsername());
                        }
                    }
                }
            }

            // *========数据库日志=========*//
            String clientIp = IpUtil.getClientIp();
            String ipRegion = IpUtil.getIpRegion(clientIp);

            final UserAgent userAgent = UserAgent.parseUserAgentString(SpringMVCUtil.getRequest().getHeader("User-Agent"));
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();

            SysOperLog operLog = new SysOperLog();
            operLog.setOperName(authUser.getNickname());
            operLog.setDeptName(authUser.getDeptName());
            operLog.setReqIp(clientIp);
            operLog.setReqRegion(ipRegion);
            operLog.setStatus(BusinessStatus.SUCCESS);
            operLog.setBrowser(browser);
            operLog.setOs(os);

            if (e != null) {
                operLog.setStatus(BusinessStatus.FAIL);
                operLog.setErrorMsg(StrUtil.sub(e.getMessage(), 0, 255));
                operLog.setErrorStack(ExceptionUtil.stacktraceToString(e, 8000));
            }

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            operLog.setReqUrl(StrUtil.sub(SpringMVCUtil.getRequest().getRequestURI(), 0, 255));
            // 设置请求方式
            operLog.setReqMethod(SpringMVCUtil.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            // 设置消耗时间
            operLog.setExecTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            // 保存数据库
            sysOperLogService.save(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}" , exp.getMessage());
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog, Object jsonResult) throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.businessType());
        // 设置标题
        operLog.setTitle(log.title());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog, log.excludeParamNames());
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && jsonResult != null) {
            operLog.setRespResult(StrUtil.sub(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog, String[] excludeParamNames) throws Exception {
        Map<String, String> paramsMap = JakartaServletUtil.getParamMap(SpringMVCUtil.getRequest());
        String requestMethod = operLog.getReqMethod();
        if (CollUtil.isEmpty(paramsMap) && (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))) {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            operLog.setReqParams(StrUtil.sub(params, 0, 2000));
        } else {
            operLog.setReqParams(StrUtil.sub(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeParamNames)), 0, 2000));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (o != null && !isFilterObject(o)) {
                    try {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params.append(jsonObj).append(" ");
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 忽略敏感属性
     */
    public PropertyFilter excludePropertyPreFilter(String[] excludeParamNames) {
        return (source, name, value) -> {
            String[] strings = ArrayUtil.addAll(EXCLUDE_PROPERTIES, excludeParamNames);
            return !ArrayUtil.contains(strings, name);
        };
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
