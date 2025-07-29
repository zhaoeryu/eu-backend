package cn.eu.common.annotation;

import cn.eu.common.sensitive.DefaultSensitiveStrategy;
import cn.eu.common.sensitive.SensitiveSerializer;
import cn.eu.common.sensitive.SensitiveStrategy;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据脱敏
 * @author Eu.z
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerializer.class)
public @interface Sensitive {

    /**
     * 脱敏规则
     */
    Type type() default Type.STRATEGY;

    /**
     * 开始位置（包含）
     */
    int startInclude() default 0;

    /**
     * 结束位置（不包含）
     */
    int endInclude() default 0;

    /**
     * 脱敏策略
     */
    Class<? extends SensitiveStrategy> strategy() default DefaultSensitiveStrategy.class;

    enum Type {
        /**
         * 策略模式
         */
        STRATEGY,
        /**
         * 自定义
         */
        CUSTOM,
        /**
         * 中文名
         */
        CHINESE_NAME,
        /**
         * 身份证号
         */
        ID_CARD,
        /**
         * 座机号
         */
        FIXED_PHONE,
        /**
         * 手机号
         */
        MOBILE_PHONE,
        /**
         * 地址
         */
        ADDRESS,
        /**
         * 电子邮件
         */
        EMAIL,
        /**
         * 密码
         */
        PASSWORD,
        /**
         * 中国大陆车牌
         */
        CAR_LICENSE,
        /**
         * 银行卡
         */
        BANK_CARD,
        /**
         * IPv4地址
         */
        IPV4,
        /**
         * IPv6地址
         */
        IPV6,
        /**
         * 只显示第一个字符。
         */
        FIRST_MASK
    }

}
