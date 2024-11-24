package cn.eu.common.utils.easyexcel;

import cn.eu.common.enums.SysUserSex;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/8/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyExcelWriteSheet {

    private WriteSheet writeSheet;
    private Collection<?> dataList;

    public static EasyExcelWriteSheet of(WriteSheet writeSheet, Collection<?> dataList) {
        return new EasyExcelWriteSheet(writeSheet, dataList);
    }

    public EasyExcelWriteSheet registerStandardWriteHandler() {
        EasyExcelUtil.registerStandardWriteHandler(writeSheet);
        return this;
    }

    public static <T> EasyExcelWriteSheet of(WriteSheet writeSheet, List<T> dataList, List<EasyExcelCellItem<T>> cellItems) {
        EasyExcelHeadContent easyExcelHeadContent = EasyExcelUtil.buildHeadContent(dataList, cellItems);
        writeSheet.setHead(easyExcelHeadContent.getHeadList());
        return new EasyExcelWriteSheet(writeSheet, easyExcelHeadContent.getContentList());
    }

}
