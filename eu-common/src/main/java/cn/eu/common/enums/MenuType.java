package cn.eu.common.enums;

import cn.eu.common.annotation.IEuEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhaoeryu
 * @since 2023/6/4
 */
@Getter
@AllArgsConstructor
public enum MenuType implements IEuEnum<Integer> {

    /**
    * 目录
    */
    CATALOG(1, "目录"),

    /**
    * 菜单
    */
    MENU(2, "菜单"),

    /**
    * 按钮
    */
    BUTTON(3, "按钮");

    private final Integer value;
    private final String desc;
}
