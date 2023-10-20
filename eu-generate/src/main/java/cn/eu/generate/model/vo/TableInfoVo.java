package cn.eu.generate.model.vo;

import cn.eu.generate.domain.GenTable;
import cn.eu.generate.domain.GenTableColumn;
import lombok.Data;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
@Data
public class TableInfoVo {

    private GenTable table;
    private List<GenTableColumn> columns;

}
