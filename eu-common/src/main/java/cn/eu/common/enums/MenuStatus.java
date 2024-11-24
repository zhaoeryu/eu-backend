package cn.eu.common.enums;

import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
@Getter
public enum MenuStatus {

    /**
    * 正常
    */
    NORMAL(0),

    /**
    * 禁用
    */
    DISABLE(1);

    private int value;

    MenuStatus(int value) {
        this.value = value;
    }
}
