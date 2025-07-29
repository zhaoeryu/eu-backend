package cn.eu.common.sensitive;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 默认脱敏策略
 *
 * @author Eu.z
 * @since 2025/7/29
 */
public class DefaultSensitiveStrategy implements SensitiveStrategy {
    @Override
    public String sensitive(String value) {
        if (value == null) {
            return null;
        }
        return StrUtil.hide(value, 0, RandomUtil.randomInt(0, value.length()));
    }

}
