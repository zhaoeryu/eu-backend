package cn.eu.common.enums;

import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
@Getter
public enum MenuType {

    /**
    * 目录
    */
    CATALOG(1),

    /**
    * 菜单
    */
    MENU(2),

    /**
    * 按钮
    */
    BUTTON(3);

    private int value;

    MenuType(int value) {
        this.value = value;
    }
}
