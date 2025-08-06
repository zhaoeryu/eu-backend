package cn.eu.generate.constants;

import cn.eu.common.core.domain.BaseEntity;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
public class GenConstant {

    public static final String COMMON_ENTITY_FIELD_ID = "id";
    public static final String COMMON_ENTITY_FIELD_CREATE_BY = BaseEntity.FIELD_CREATE_BY;
    public static final String COMMON_ENTITY_FIELD_UPDATE_BY = BaseEntity.FIELD_UPDATE_BY;
    public static final String COMMON_ENTITY_FIELD_CREATE_TIME = BaseEntity.FIELD_CREATE_TIME;
    public static final String COMMON_ENTITY_FIELD_UPDATE_TIME = BaseEntity.FIELD_UPDATE_TIME;
    public static final String COMMON_ENTITY_FIELD_DEL_FLAG = BaseEntity.FIELD_DEL_FLAG;
    public static final String COMMON_ENTITY_FIELD_REMARK = BaseEntity.FIELD_REMARK;

    public static final String DEFAULT_CONVERT_FIELD_CREATE_BY = BaseEntity.TRANS_FIELD_CREATE_BY;
    public static final String DEFAULT_CONVERT_FIELD_UPDATE_BY = BaseEntity.TRANS_FIELD_UPDATE_BY;

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
     * BaseEntity里的字段
     */
    public static final String[] BASE_ENTITY_FIELD_LIST = {
            COMMON_ENTITY_FIELD_CREATE_BY,
            COMMON_ENTITY_FIELD_UPDATE_BY,
            COMMON_ENTITY_FIELD_CREATE_TIME,
            COMMON_ENTITY_FIELD_UPDATE_TIME,
            COMMON_ENTITY_FIELD_DEL_FLAG,
            COMMON_ENTITY_FIELD_REMARK,
    };

    /**
     * 模版文件类型
     */
    public static final String TPL_FILE_TYPE_JAVA = "java";
    public static final String TPL_FILE_TYPE_XML = "xml";
    public static final String TPL_FILE_TYPE_VUE = "vue";
    public static final String TPL_FILE_TYPE_JS = "js";
    public static final String TPL_FILE_TYPE_TS = "ts";
    public static final String TPL_FILE_TYPE_SQL = "sql";
    public static final String TPL_FILE_TYPE_PROPERTIES = "properties";
}
