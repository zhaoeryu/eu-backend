package cn.eu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * crud编辑模式
 *
 * @author Eu.z
 * @since 2025/2/28
 */
@Getter
@AllArgsConstructor
public enum CrudEditMode {

    DIALOG("dialog", "弹窗"),
    PAGE("page", "页面");

    private String value;
    private String label;
}
