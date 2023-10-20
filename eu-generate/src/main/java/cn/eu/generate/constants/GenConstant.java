package cn.eu.generate.constants;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
public class GenConstant {

    public static final String UTF8 = "UTF-8";

    /**
     * 主键列
     */
    public static final String PK_COLUMN_KEY = "PRI";

    /**
     * 自增长
     */
    public static final String PK_AUTO_INCREMENT = "auto_increment";

    /**
     * 默认包名
     */
    public static final String DEFAULT_PACKAGE_NAME = "cn.eu.business";

    /**
     * 默认模块名
     */
    public static final String DEFAULT_MODULE_NAME = "eu-admin";

    /**
     * 字段黑名单：默认表格不显示的字段
     */
    public static final String[] BLACK_LIST_FIELD_TABLE = { "id", "create_by", "update_by", "del_flag", "remark" };
    /**
     * 字段黑名单：默认表单不显示的字段
     */
    public static final String[] BLACK_LIST_FIELD_FORM = { "id", "create_by", "update_by", "del_flag", "remark", "create_time", "update_time" };
    /**
     * 字段黑名单：默认导出不显示的字段
     */
    public static final String[] BLACK_LIST_FIELD_EXPORT = { "create_by", "update_by", "del_flag" };
    /**
     * BaseEntity里的字段
     */
    public static final String[] BASE_ENTITY_FIELD_LIST = { "create_by", "update_by", "del_flag", "remark", "create_time", "update_time"  };

    /**
     * 模版文件类型
     */
    public static final String TPL_FILE_TYPE_JAVA = "java";
    public static final String TPL_FILE_TYPE_XML = "xml";
    public static final String TPL_FILE_TYPE_VUE = "vue";
    public static final String TPL_FILE_TYPE_JS = "js";
    public static final String TPL_FILE_TYPE_SQL = "sql";
}
