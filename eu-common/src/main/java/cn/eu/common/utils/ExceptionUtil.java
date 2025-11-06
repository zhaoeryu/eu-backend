package cn.eu.common.utils;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 异常工具类
 *
 * @author Eu.z
 * @since 2025/11/6
 */
public class ExceptionUtil {

    static final String REGX_DUPLICATE_KEY_MYSQL = "Duplicate entry '(.*)' for key '(.*)'";
    static final String REGX_DUPLICATE_KEY_PGSQL = "unique constraint \"([^\"]+)\"[\\s\\S]*?=\\(([^)]+)\\)";

    public static String getDuplicateKeyMessage(String errorMessage) {
        if (MpUtil.isMysql()) {
            // 提取重复字段
            Matcher matcher = Pattern.compile(REGX_DUPLICATE_KEY_MYSQL).matcher(errorMessage);
            if (matcher.find()) {
                String duplicateField = matcher.group(2);
                String duplicateFieldValue = matcher.group(1);
                if (StrUtil.isNotBlank(duplicateField)) {
                    return StrUtil.format("[{}]({}){}", duplicateField, duplicateFieldValue, MessageUtils.message("error.duplicate_key"));
                }
            }
        }
        if (MpUtil.isPostgresql()) {
            Pattern pattern = Pattern.compile(REGX_DUPLICATE_KEY_PGSQL);
            Matcher matcher = pattern.matcher(errorMessage);

            if (matcher.find()) {
                // 提取约束名
                String duplicateField = matcher.group(1);
                // 提取键值对字符串并分割
                String duplicateFieldValue = matcher.group(2);

                return StrUtil.format("[{}]({}){}", duplicateField, duplicateFieldValue, MessageUtils.message("error.duplicate_key"));
            }
        }
        return null;
    }

}
