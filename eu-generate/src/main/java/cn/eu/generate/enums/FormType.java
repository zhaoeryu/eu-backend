package cn.eu.generate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表单类型
 * @author zhaoeryu
 * @since 2023/6/29
 */
@Getter
@AllArgsConstructor
public enum FormType {

    INPUT("input", "文本框"),
    TEXTAREA("textarea", "文本域"),
    NUMBER("number", "数字框"),
    SELECT("select", "下拉框"),
    RADIO("radio", "单选框"),
    CHECKBOX("checkbox", "复选框"),
    SWITCH("switch", "开关"),
    DATE("date", "日期"),
    DATETIME("datetime", "日期时间"),
    IMAGE("image", "图片"),
    FILE("file", "文件"),
    EDITOR("editor", "富文本");

    private String value;
    private String label;


}
