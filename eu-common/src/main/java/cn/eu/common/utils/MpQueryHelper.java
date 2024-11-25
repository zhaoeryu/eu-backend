package cn.eu.common.utils;

import cn.eu.common.constants.Constants;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * MybatisPlus查询条件构建工具类
 * @author zhaoeryu
 * @since 2023/6/3
 */
@Slf4j
public class MpQueryHelper {

    /**
     * 不校验值为空的查询类型
     */
    private static final List<Query.Type> NOT_NONNULL_QUERY_TYPE = Arrays.asList(
            Query.Type.IS_NULL,
            Query.Type.IS_NOT_NULL
    );

    /**
     * 根据查询条件的Object对象构建MybatisPlus的QueryWrapper
     * @param criteria 查询条件对象
     * @param entity 要查询的实体类Class
     * @return LambdaQueryWrapper<Entity>
     */
    public static <T> QueryWrapper<T> buildQueryWrapper(Object criteria, Class<T> entity) {
        return buildQueryWrapper(criteria, entity, null);
    }
    /**
     * 根据查询条件的Object对象构建MybatisPlus的QueryWrapper
     * @param criteria 查询条件对象
     * @param entity 要查询的实体类Class
     * @param tableAlias 表别名
     * @return LambdaQueryWrapper<Entity>
     */
    public static <T> QueryWrapper<T> buildQueryWrapper(Object criteria, Class<T> entity, String tableAlias) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (criteria == null) {
            return queryWrapper;
        }

        try {
            // 获取criteria(包括父类)的所有字段
            List<Field> fields = getAllFields(criteria);

            // 遍历所有的字段，并根据@Query注解填充QueryWrapper查询条件
            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                // 设置可访问
                field.setAccessible(true);
                Query annotation = field.getAnnotation(Query.class);
                if (annotation != null) {
                    Object value = field.get(criteria);
                    Query.Type queryType = annotation.type();

                    // 判断值是否为空
                    boolean isNonNull = value != null && !(value instanceof String && StrUtil.isBlank((String) value));

                    // 判断查询类型是否不需要校验空值
                    boolean isNotNonNull = NOT_NONNULL_QUERY_TYPE.contains(queryType);

                    if (isNotNonNull || isNonNull) {
                        String fieldName = field.getName();

                        // 设置查询条件
                        fillQueryWrapper(queryWrapper, queryType, value, fieldName, tableAlias);
                    }
                }
                // 恢复可访问设置
                field.setAccessible(accessible);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return queryWrapper;
    }

    /**
     * 根据查询条件的Object对象构建MybatisPlus的QueryWrapper,并在最后附件上逻辑删除的查询条件
     * @param criteria 查询条件对象
     * @param entity 要查询的实体类Class
     * @param tableAlias 表别名
     * @return LambdaQueryWrapper<Entity>
     */
    public static <T> QueryWrapper<T> buildQueryWrapperWithDelFlag(Object criteria, Class<T> entity, String tableAlias) {
        QueryWrapper<T> queryWrapper = buildQueryWrapper(criteria, entity, tableAlias);
        // 如果在Mapper里使用${ew.customSqlSegment}这样方式拼接查询条件，@TableLogic不会生效所以需要手动添加
        queryWrapper.eq(wrapperAliasField(tableAlias, StrUtil.toUnderlineCase(Constants.DEL_FLAG_FIELD_NAME)), Constants.DEL_FLAG_NORMAL);
        return queryWrapper;
    }

    /**
     * 根据字段上Query.Type的类型，为QueryWrapper设置查询条件
     * @param queryWrapper QueryWrapper
     * @param queryType Query.Type
     * @param value 查询值
     * @param attributeName 查询字段名
     * @param tableAlias 表别名
     * @param <T> 实体类
     */
    private static <T> void fillQueryWrapper(QueryWrapper<T> queryWrapper, Query.Type queryType, Object value, String attributeName, String tableAlias) {
        String fieldName = StrUtil.toUnderlineCase(attributeName);
        fieldName = wrapperAliasField(tableAlias, fieldName);
        switch (queryType) {
            case EQ:
                queryWrapper.eq(fieldName, value);
                break;
            case GT:
                queryWrapper.gt(fieldName, value);
                break;
            case GE:
                queryWrapper.ge(fieldName, value);
                break;
            case LT:
                queryWrapper.lt(fieldName, value);
                break;
            case LE:
                queryWrapper.le(fieldName, value);
                break;
            case NE:
                queryWrapper.ne(fieldName, value);
                break;
            case LIKE:
                queryWrapper.like(fieldName, value);
                break;
            case LEFT_LIKE:
                queryWrapper.likeLeft(fieldName, value);
                break;
            case RIGHT_LIKE:
                queryWrapper.likeRight(fieldName, value);
                break;
            case IN:
                if (value instanceof Collection) {
                    queryWrapper.in(fieldName, (Collection<?>) value);
                } else {
                    queryWrapper.in(fieldName, value);
                }
                break;
            case NOT_IN:
                if (value instanceof Collection) {
                    queryWrapper.notIn(fieldName, (Collection<?>) value);
                } else {
                    queryWrapper.notIn(fieldName, value);
                }
                break;
            case IS_NULL:
                queryWrapper.isNull(fieldName);
                break;
            case IS_NOT_NULL:
                queryWrapper.isNotNull(fieldName);
                break;
            case BETWEEN:
                if (value instanceof Collection) {
                    // 取出第一个和第二个值，如果不是两个值，就不处理，如果是两个值，并且不为空，就处理
                    Object[] values = ((Collection<?>) value).toArray();
                    if (ArrayUtil.isNotEmpty(values)) {
                        if (values.length == 2 && values[0] != null && values[1] != null) {
                            queryWrapper.between(fieldName, values[0], values[1]);
                        } else {
                            log.warn("BETWEEN条件需要两个值，当前值为：{}", value);
                        }
                    }
                }
                break;
            default:
                // nothing
                log.warn("未支持的查询类型：{}", queryType.name());
                break;
        }
    }

    private static String wrapperAliasField(String tableAlias, String fieldName) {
        if (StrUtil.isNotBlank(tableAlias)) {
            return tableAlias + "." + fieldName;
        }
        return fieldName;
    }

    /**
     * 获取对象的所有字段，包括父类的字段
     * @param entity 要获取字段的对象
     * @return List<Field> 该对象的所有字段
     */
    private static List<Field> getAllFields(Object entity) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

}
