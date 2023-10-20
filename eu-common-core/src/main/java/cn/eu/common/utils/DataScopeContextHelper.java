package cn.eu.common.utils;

/**
 * @author zhaoeryu
 * @since 2023/8/3
 */
public class DataScopeContextHelper {

    private static final InheritableThreadLocal<String> LOCAL_DATA_SCOPE_SQL = new InheritableThreadLocal<>();

    public static void set(String sql) {
        LOCAL_DATA_SCOPE_SQL.set(sql);
    }

    public static String get() {
        return LOCAL_DATA_SCOPE_SQL.get();
    }

    public static void clear() {
        LOCAL_DATA_SCOPE_SQL.remove();
    }

}
