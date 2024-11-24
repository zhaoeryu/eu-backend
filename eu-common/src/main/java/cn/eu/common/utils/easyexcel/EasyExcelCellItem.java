package cn.eu.common.utils.easyexcel;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Function;

/**
 * @author zhaoeryu
 */
@Data
@AllArgsConstructor
public class EasyExcelCellItem<T> {
    private String columnName;
    private Function<T, ?> cellValueFunc;

    public static <T> EasyExcelCellItem<T> of(String columnName, Function<T, ?> cellValueFunc) {
        return new EasyExcelCellItem<>(columnName, cellValueFunc);
    }

}
    