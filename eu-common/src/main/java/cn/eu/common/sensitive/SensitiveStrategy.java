package cn.eu.common.sensitive;

/**
 * 脱敏策略
 * @author Eu.z
 * @since 2025/7/29
 */
public interface SensitiveStrategy {

    /**
     * 脱敏
     *
     * @param value 需要脱敏的值
     * @return 脱敏后的值
     */
    String sensitive(String value);
}
