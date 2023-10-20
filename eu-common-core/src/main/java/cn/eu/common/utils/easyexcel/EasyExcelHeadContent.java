package cn.eu.common.utils.easyexcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/8/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyExcelHeadContent {

    private List<List<String>> headList;
    private List<List<Object>> contentList;

}
