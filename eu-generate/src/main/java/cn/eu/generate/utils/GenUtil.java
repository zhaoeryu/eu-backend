package cn.eu.generate.utils;

import cn.eu.common.model.ResultBody;
import cn.eu.generate.constants.GenConstant;
import cn.eu.generate.domain.GenTableColumn;
import cn.eu.generate.enums.FormType;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
public class GenUtil {

    /**
     * 获取主键类型
     */
    public static String getPkColumnType(List<GenTableColumn> columns) {
        for (GenTableColumn column : columns) {
            if (isPkColumn(column)) {
                return FieldTypeMappingUtil.getJavaType(column.getColumnType());
            }
        }
        return null;
    }

    /**
     * 字段是否为主键
     * @param column 数据库字段
     * @return 是否为主键
     */
    public static boolean isPkColumn(GenTableColumn column) {
        return GenConstant.PK_COLUMN_KEY.equals(column.getColumnKey());
    }

    /**
     * 获取是否自增主键
     */
    public static boolean isAutoPk(List<GenTableColumn> columns) {
        // columns.autoPk 只要有一个为true就返回true，否则返回false
        return columns.stream().anyMatch(item -> BooleanUtil.isTrue(item.getAutoPk()));
    }

    /**
     * 检查字段是否可以在表格中显示
     * @param fieldName 字段名
     * @return 是否可以在表格中显示
     */
    public static boolean isFieldTableShow(String fieldName) {
        return !CollUtil.contains(Arrays.asList(
                GenConstant.COMMON_ENTITY_FIELD_DEL_FLAG
        ), fieldName);
    }

    /**
     * 检查字段是否可以在表单中显示
     * @param fieldName 字段名
     * @return 是否可以在表单中显示
     */
    public static boolean isFieldFormShow(String fieldName) {
        String[] BLACK_LIST_FIELD_FORM = {
                GenConstant.COMMON_ENTITY_FIELD_ID,
                GenConstant.COMMON_ENTITY_FIELD_CREATE_BY,
                GenConstant.COMMON_ENTITY_FIELD_UPDATE_BY,
                GenConstant.COMMON_ENTITY_FIELD_DEL_FLAG,
                GenConstant.COMMON_ENTITY_FIELD_CREATE_TIME,
                GenConstant.COMMON_ENTITY_FIELD_UPDATE_TIME,
        };
        return !CollUtil.contains(Arrays.asList(BLACK_LIST_FIELD_FORM), fieldName);
    }

    /**
     * 检查字段是否在表格中默认可见
     * @param fieldName 字段名
     * @return 是否在表格中默认可见
     */
    public static boolean isFieldInTableVisible(String fieldName) {
        String[] HIDDEN_FIELD_TABLE = {
                GenConstant.COMMON_ENTITY_FIELD_ID,
                GenConstant.COMMON_ENTITY_FIELD_CREATE_BY,
                GenConstant.COMMON_ENTITY_FIELD_UPDATE_BY,
                GenConstant.COMMON_ENTITY_FIELD_UPDATE_TIME,
                GenConstant.COMMON_ENTITY_FIELD_DEL_FLAG,
        };
        return !CollUtil.contains(Arrays.asList(HIDDEN_FIELD_TABLE), fieldName);
    }

    /**
     * 检查字段是否可以导出
     * @param fieldName 字段名
     * @return 是否可以导出
     */
    public static boolean isFieldExport(String fieldName) {
        return !CollUtil.contains(Arrays.asList(
                GenConstant.COMMON_ENTITY_FIELD_DEL_FLAG
        ), fieldName);
    }

    /**
     * 检查字段是否在BaseEntity中
     */
    public static boolean isFieldBaseEntity(String fieldName) {
        String[] BASE_ENTITY_FIELD_LIST = {
                GenConstant.COMMON_ENTITY_FIELD_CREATE_BY,
                GenConstant.COMMON_ENTITY_FIELD_UPDATE_BY,
                GenConstant.COMMON_ENTITY_FIELD_CREATE_TIME,
                GenConstant.COMMON_ENTITY_FIELD_UPDATE_TIME,
                GenConstant.COMMON_ENTITY_FIELD_DEL_FLAG,
                GenConstant.COMMON_ENTITY_FIELD_REMARK,
        };
        return CollUtil.contains(Arrays.asList(BASE_ENTITY_FIELD_LIST), fieldName);
    }

    /**
     * 下划线转大驼峰
     */
    public static String underlineToBigCamel(String str) {
        return StrUtil.upperFirst(StrUtil.toCamelCase(str));
    }

    /**
     * 下划线转小驼峰
     */
    public static String underlineToCamel(String str) {
        return StrUtil.toCamelCase(str);
    }

    /**
     * 各个单词之间通过下划线“-”连接
     */
    public static String underlineToKebabCase(String str) {
        return StrUtil.toSymbolCase(StrUtil.toCamelCase(str), '-');
    }

    /**
     * 生成的代码写入到response中
     */
    public static void write(byte[] bytes, HttpServletResponse response) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"eubackend-gen.zip\"");
        response.addHeader("Content-Length", "" + bytes.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(bytes, response.getOutputStream());
    }

    /**
     * 响应成功
     */
    public static void responseSuccess(HttpServletResponse response) throws IOException {
        byte[] bytes = ResultBody.ok().msg("生成成功").toJsonString().getBytes(StandardCharsets.UTF_8);
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Content-Length", "" + bytes.length);
        response.setContentType("application/json; charset=UTF-8");
        IOUtils.write(bytes, response.getOutputStream());
    }
    /**
     * 判断column的formType是否内置的表单类型
     */
    public static boolean isBuiltInFormType(String formType) {
        if (StrUtil.isBlank(formType)) {
            return true;
        }
        // 内置的表单类型
        Set<String> builtInFormTypeList = Arrays.stream(FormType.values()).map(FormType::getValue)
                .collect(Collectors.toSet());
        return builtInFormTypeList.contains(formType);
    }

}
