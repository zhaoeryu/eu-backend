package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/8/3
 */
@Getter
@AllArgsConstructor
public enum DataScopeType {

    DATA_SCOPE_ALL(1, "全部数据权限"),
    DATA_SCOPE_CUSTOM(2, "自定数据权限"),
    DATA_SCOPE_DEPT(3, "部门数据权限"),
    DATA_SCOPE_DEPT_AND_CHILD(4, "部门及以下数据权限"),
    DATA_SCOPE_SELF(5, "仅本人数据权限");

    private final Integer code;
    private final String desc;

    public static DataScopeType parseValue(Integer code) {
        if (code == null) {
            return null;
        }
        for (DataScopeType value : DataScopeType.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
