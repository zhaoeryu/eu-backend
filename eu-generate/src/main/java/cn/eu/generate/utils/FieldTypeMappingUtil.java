package cn.eu.generate.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
public class FieldTypeMappingUtil {

    /**
     * 字段类型映射文件
     */
    private static final Props PROPS = PropsUtil.get("field-type-mapping.properties");;
    private static final Props PROPS_JS = PropsUtil.get("field-type-js.properties");;

    /**
     * 根据数据库字段类型获取java类型
     * @param dbType 数据库字段类型
     * @return java类型
     */
    public static String getJavaType(String dbType) {
        return PROPS.getProperty(getDbType(dbType));
    }

    /**
     * 根据数据库字段类型获取js类型
     * @param dbType 数据库字段类型
     * @return js类型
     */
    public static String getJsType(String dbType) {
        return PROPS_JS.getProperty(getDbType(dbType));
    }

    public static String getDbType(String dbType) {
        // 去掉括号及括号里的内容
        return dbType.replaceAll("\\(.*\\)", "");
    }

    /**
     * 根据数据库字段类型获取字段长度
     * @param dbType 数据库字段类型
     * @return 字段长度
     */
    public static Integer getFieldLength(String dbType) {
        // 获取括号里面的内容
        String lenStr = dbType.replaceAll(".*\\((.*)\\)", "$1");
        lenStr = BooleanUtil.toString(lenStr.equals(dbType), null, lenStr);
        // 获取长度，忽略精度
        if (StrUtil.contains(lenStr, ",")) {
            lenStr = StrUtil.subBefore(lenStr, ",", false);
        }
        return StrUtil.isBlank(lenStr) ? null : Integer.valueOf(lenStr);
    }

    public static void main(String[] args) {
//        System.out.println(getJavaType("varchar(255)"));
//        System.out.println(getJavaType("datetime"));
//        System.out.println(getJavaType("timestamp"));
//        System.out.println(getJavaType("date"));
//
//        System.out.println("=====================================");

        String str = "varchar(20)\n" +
                "varchar(32)\n" +
                "varchar(32)\n" +
                "varchar(64)\n" +
                "tinyint(1)\n" +
                "tinyint(1)\n" +
                "tinyint(1)\n" +
                "varchar(255)\n" +
                "varchar(64)\n" +
                "varchar(64)\n" +
                "varchar(512)\n" +
                "bit(1)\n" +
                "varchar(255)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "varchar(20)\n" +
                "varchar(20)\n" +
                "varchar(32)\n" +
                "varchar(255)\n" +
                "varchar(64)\n" +
                "varchar(64)\n" +
                "varchar(512)\n" +
                "bit(1)\n" +
                "varchar(512)\n" +
                "varchar(2048)\n" +
                "datetime\n" +
                "datetime\n" +
                "bigint(20)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "varchar(20)\n" +
                "int(11)\n" +
                "varchar(50)\n" +
                "tinyint(1)\n" +
                "int(10)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "varchar(20)\n" +
                "tinyint(1)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "int(11)\n" +
                "varchar(30)\n" +
                "varchar(32)\n" +
                "varchar(30)\n" +
                "int(10)\n" +
                "tinyint(1)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "varchar(20)\n" +
                "varchar(255)\n" +
                "int(10)\n" +
                "varchar(255)\n" +
                "varchar(255)\n" +
                "varchar(255)\n" +
                "varchar(255)\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "bit(1)\n" +
                "bit(1)\n" +
                "bit(1)\n" +
                "bit(1)\n" +
                "bit(1)\n" +
                "varchar(5)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "varchar(20)\n" +
                "varchar(20)\n" +
                "tinyint(1)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "varchar(30)\n" +
                "varchar(20)\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "int(11)\n" +
                "varchar(20)\n" +
                "varchar(20)\n" +
                "varchar(10)\n" +
                "varchar(512)\n" +
                "varchar(11)\n" +
                "varchar(50)\n" +
                "varchar(128)\n" +
                "tinyint(1)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "tinyint(1)\n" +
                "varchar(128)\n" +
                "datetime\n" +
                "datetime\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(20)\n" +
                "datetime\n" +
                "varchar(255)\n" +
                "tinyint(1)\n" +
                "int(11)\n" +
                "varchar(20)\n" +
                "int(11)\n" +
                "varchar(20)";
        CollUtil.newArrayList(str.split("\n")).forEach(s -> {
            System.out.println(getJsType(s));
        });

//        System.out.println(getFormType("VARCHAR(32)"));
//        System.out.println(getFormType("varchar(512)"));
//        System.out.println(getFormType("datetime"));
//        System.out.println(getFormType("date"));
//        System.out.println(getFormType("timestamp"));
//        System.out.println(getFormType("bit(1)"));
    }
}
