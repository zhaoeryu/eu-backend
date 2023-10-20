package cn.eu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 导入模式
 * @author zhaoeryu
 * @since 2023/8/23
 */
@Getter
@AllArgsConstructor
public enum ImportMode {

    ONLY_ADD(0, "仅新增"),
    ONLY_UPDATE(1, "仅更新"),
    ADD_AND_UPDATE(2, "新增和更新");

    private Integer code;
    private String label;
}
