package cn.eu.common.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoeryu
 * @since 2023/10/24
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    /**
     * 动态数据源名称上下文
     */
    private static final ThreadLocal<String> CONTEXT_KEY_HOLDER = new ThreadLocal<>();

    /**
     * 设置/切换数据源
     * @see DSConstants
     */
    public static void setContextKey(String key) {
        log.info("切换数据源：{}", key);
        CONTEXT_KEY_HOLDER.set(key);
    }

    /**
     * 获取数据源名称
     */
    public static String getContextKey() {
        String key = CONTEXT_KEY_HOLDER.get();
        return key == null ? DSConstants.MASTER : key;
    }

    /**
     * 删除当前数据源名称
     */
    public static void removeContextKey() {
        CONTEXT_KEY_HOLDER.remove();
    }
}
