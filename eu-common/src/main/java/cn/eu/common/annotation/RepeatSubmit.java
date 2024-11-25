package cn.eu.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * 间隔时间，小于此时间视为重复提交
     */
    int interval() default 5000;

    /**
     * 时间单位，默认(ms)
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

    /**
     * 提示消息 支持国际化
     */
    String message() default "{error.repeat.submit}";

}
