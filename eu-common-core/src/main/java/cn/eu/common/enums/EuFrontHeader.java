package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 前端请求头
 *
 * @author zhaoeryu
 * @since 2023/10/20
 */
@Getter
@AllArgsConstructor
public enum EuFrontHeader {

    VUE2("vue2"),
    VUE3("vue3");

    private final String desc;

}
