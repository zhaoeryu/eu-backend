package cn.eu.common.datasource.aop;

import cn.eu.common.datasource.DS;
import cn.eu.common.datasource.DynamicDataSourceContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Objects;

/**
 * @author zhaoeryu
 * @since 2023/10/25
 */
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            DS annotation = Objects.requireNonNull(methodInvocation.getThis()).getClass().getAnnotation(DS.class);
            if (annotation == null) {
                annotation = methodInvocation.getMethod().getAnnotation(DS.class);
            }
            DynamicDataSourceContextHolder.setContextKey(annotation.value());
            return methodInvocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }
}
