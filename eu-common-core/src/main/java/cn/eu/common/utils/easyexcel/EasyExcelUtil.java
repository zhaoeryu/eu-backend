package cn.eu.common.utils.easyexcel;

import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/8/25
 */
public class EasyExcelUtil {

    /**
     * 构建Excel头部和内容
     */
    public static <T> EasyExcelHeadContent buildHeadContent(List<T> dataList, List<EasyExcelCellItem<T>> easyExcelCellItems) {
        // 获取头部
        List<List<String>> headList = easyExcelCellItems.stream().map(item -> {
            List<String> head = ListUtils.newArrayList();
            head.add(item.getColumnName());
            return head;
        }).collect(Collectors.toList());

        // 获取内容
        List<List<Object>> rows = Optional.ofNullable(dataList).orElse(new ArrayList<>()).stream().map(item -> {
            List<Object> data = new ArrayList<>();
            for (EasyExcelCellItem<T> cellItem : easyExcelCellItems) {
                Object cellValue = cellItem.getCellValueFunc().apply(item);
                data.add(cellValue);
            }
            return data;
        }).collect(Collectors.toList());

        return new EasyExcelHeadContent(headList, rows);
    }

    public static void registerStandardWriteHandler(WriteSheet writeSheet) {
        writeSheet.getCustomWriteHandlerList().add(headCellStyleStrategy());
        writeSheet.getCustomWriteHandlerList().add(autoColumnWidthCellStyleStrategy());
    }

    public static CellWriteHandler autoColumnWidthCellStyleStrategy() {
        return new AutoColumnWidthCellStyleStrategy();
    }

    public static AbstractCellStyleStrategy headCellStyleStrategy() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为灰色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)10);
        headWriteFont.setColor(IndexedColors.WHITE.getIndex());
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 忽略边框
        headWriteCellStyle.setBorderBottom(BorderStyle.NONE);
        headWriteCellStyle.setBorderTop(BorderStyle.NONE);
        headWriteCellStyle.setBorderLeft(BorderStyle.NONE);
        headWriteCellStyle.setBorderRight(BorderStyle.NONE);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints((short)10);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

}
