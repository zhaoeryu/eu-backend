package cn.eu.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * I18n工具类
 *
 * @author Eu.z
 * @since 2024/11/18
 */
public class MessageUtils {

    private static final MessageSource MESSAGE_SOURCE = SpringContextHolder.getBean(MessageSource.class);

    /**
     * 根据消息键和参数 获取消息
     *
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args) {
        return MESSAGE_SOURCE.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * 解析消息键 获取消息, 格式为{code}
     */
    public static String parseMessage(String code, Object... args) {
        if (code.startsWith("{") && code.endsWith("}")) {
            return message(code.substring(1, code.length() - 1), args);
        }
        return message(code, args);
    }

}
