package cn.eu.common.utils.easyexcel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/10
 */
public class AutoColumnWidthCellStyleStrategy extends AbstractColumnWidthStyleStrategy {
    private int MAX_COLUMN_WIDTH = 100;
    private int MIN_COLUMN_WIDTH = 20;

    public AutoColumnWidthCellStyleStrategy () {

    }
    public AutoColumnWidthCellStyleStrategy(Integer maxColumnWidth, Integer minColumnWidth) {
        if (maxColumnWidth != null) {
            this.MAX_COLUMN_WIDTH = maxColumnWidth;
        }
        if (minColumnWidth != null) {
            this.MIN_COLUMN_WIDTH = minColumnWidth;
        }
    }

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell
            cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            int columnWidth = cell.getStringCellValue().getBytes().length;
            if (columnWidth > MAX_COLUMN_WIDTH) {
                columnWidth = MAX_COLUMN_WIDTH;
            } else if (columnWidth <= MIN_COLUMN_WIDTH) {
                columnWidth = MIN_COLUMN_WIDTH;
            }else {
                if (cell.getColumnIndex() == 1) {
                    columnWidth = columnWidth + 10;
                } else {
                    columnWidth = columnWidth + 3;
                }
            }
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
        }
    }

}
