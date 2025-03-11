package cn.eu.generate.constants;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
public class GenConstant {

    public static final String COMMON_ENTITY_FIELD_ID = "id";
    public static final String COMMON_ENTITY_FIELD_CREATE_BY = "create_by";
    public static final String COMMON_ENTITY_FIELD_UPDATE_BY = "update_by";
    public static final String COMMON_ENTITY_FIELD_CREATE_TIME = "create_time";
    public static final String COMMON_ENTITY_FIELD_UPDATE_TIME = "update_time";
    public static final String COMMON_ENTITY_FIELD_DEL_FLAG = "del_flag";
    public static final String COMMON_ENTITY_FIELD_REMARK = "remark";
    public static final String COMMON_ENTITY_FIELD_DATA_SOURCE = "data_source";
    public static final String COMMON_ENTITY_FIELD_STATUS = "status";

    public static final String DEFAULT_CONVERT_FIELD_CREATE_BY = "createByNickname";
    public static final String DEFAULT_CONVERT_FIELD_UPDATE_BY = "updateByNickname";

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
    public static final String[] BLACK_LIST_FIELD_TABLE = {
            COMMON_ENTITY_FIELD_ID,
            COMMON_ENTITY_FIELD_DEL_FLAG
    };
    /**
     * 字段黑名单：默认表单不显示的字段
     */
    public static final String[] BLACK_LIST_FIELD_FORM = {
            COMMON_ENTITY_FIELD_ID,
            COMMON_ENTITY_FIELD_CREATE_BY,
            COMMON_ENTITY_FIELD_UPDATE_BY,
            COMMON_ENTITY_FIELD_DEL_FLAG,
            COMMON_ENTITY_FIELD_REMARK,
            COMMON_ENTITY_FIELD_CREATE_TIME,
            COMMON_ENTITY_FIELD_UPDATE_TIME,
            COMMON_ENTITY_FIELD_DATA_SOURCE,
            COMMON_ENTITY_FIELD_STATUS
    };
    /**
     * 字段黑名单：默认导出不显示的字段
     */
    public static final String[] BLACK_LIST_FIELD_EXPORT = {
            COMMON_ENTITY_FIELD_DEL_FLAG
    };
    /**
     * BaseEntity里的字段
     */
    public static final String[] BASE_ENTITY_FIELD_LIST = {
            COMMON_ENTITY_FIELD_CREATE_BY,
            COMMON_ENTITY_FIELD_UPDATE_BY,
            COMMON_ENTITY_FIELD_DEL_FLAG,
            COMMON_ENTITY_FIELD_REMARK,
            COMMON_ENTITY_FIELD_CREATE_TIME,
            COMMON_ENTITY_FIELD_UPDATE_TIME
    };
    /**
     * 表格默认隐藏的字段
     */
    public static final String[] HIDDEN_FIELD_TABLE = {
            COMMON_ENTITY_FIELD_ID,
            COMMON_ENTITY_FIELD_CREATE_BY,
            COMMON_ENTITY_FIELD_UPDATE_BY,
            COMMON_ENTITY_FIELD_CREATE_TIME,
            COMMON_ENTITY_FIELD_UPDATE_TIME,
            COMMON_ENTITY_FIELD_DEL_FLAG,
            COMMON_ENTITY_FIELD_REMARK,
            COMMON_ENTITY_FIELD_DATA_SOURCE,
            COMMON_ENTITY_FIELD_STATUS
    };

    /**
     * 模版文件类型
     */
    public static final String TPL_FILE_TYPE_JAVA = "java";
    public static final String TPL_FILE_TYPE_XML = "xml";
    public static final String TPL_FILE_TYPE_VUE = "vue";
    public static final String TPL_FILE_TYPE_JS = "js";
    public static final String TPL_FILE_TYPE_SQL = "sql";
    public static final String TPL_FILE_TYPE_PROPERTIES = "properties";
}
