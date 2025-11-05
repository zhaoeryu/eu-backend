package cn.eu.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoeryu
 * @since 2023/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionItem<T> {

    private String label;
    private T value;
    private Object raw;

    public OptionItem(String label, T value) {
        this.label = label;
        this.value = value;
    }
}