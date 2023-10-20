package cn.eu.common.annotation;

import java.lang.annotation.*;

/**
 * @author zhaoeryu
 * @since 2023/8/3
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 本人数据权限时，表的别名
     */
    String userAlias() default "";
    String userField() default "";

    /**
     * 部门表的别名
     */
    String deptAlias() default "";

    /**
     * 是否使用MybatisPlus单表查询
     * 如果涉及到多表查询，该字段必须为false
     */
    boolean isSingleQuery() default false;
}
