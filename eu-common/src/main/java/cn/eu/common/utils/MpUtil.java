package cn.eu.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;

/**
 * MybatisPlus工具类
 *
 * @author Eu.z
 * @since 2025/11/6
 */
public class MpUtil {

    /**
     * 获取数据库类型
     * @return 数据库类型
     */
    public static DbType getDbType() {
        MybatisPlusProperties properties = SpringContextHolder.getBean(MybatisPlusProperties.class);
        return getDbType(properties);
    }
    public static DbType getDbType(MybatisPlusProperties properties) {
        return DbType.getDbType(properties.getConfiguration().getDatabaseId());
    }

    public static boolean isPostgresql() {
        return DbType.POSTGRE_SQL.equals(getDbType());
    }
    public static boolean isMysql() {
        return DbType.MYSQL.equals(getDbType());
    }
}
