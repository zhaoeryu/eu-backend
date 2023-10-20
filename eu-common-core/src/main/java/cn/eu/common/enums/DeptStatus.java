package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DeptStatus {

    /**
     * 正常
     */
    NORMAL(0),

    /**
     * 禁用
     */
    DISABLE(1);

    private int value;

}
