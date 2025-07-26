package cn.eu.common.utils;

import java.lang.annotation.*;

/**
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    Type type() default Type.EQ;

    /**
     * 指定数据库中的字段名。如果你的实体类字段名与数据库字段名不同，使用这个属性来指定正确的数据库字段名。
     */
    String value() default "";

    enum Type {
        // =
        EQ,
        // >
        GT,
        // >=
        GE,
        // <
        LT,
        // <=
        LE,
        // "%?%"
        LIKE,
        // "%?"
        LEFT_LIKE,
        // "?%"
        RIGHT_LIKE,
        // in
        IN,
        // not in
        NOT_IN,
        // between
        BETWEEN,
        // is null
        IS_NULL,
        // is not null
        IS_NOT_NULL,
        // <>
        NE
    }
}
