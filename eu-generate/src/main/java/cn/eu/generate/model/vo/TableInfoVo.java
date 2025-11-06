package cn.eu.generate.model.vo;

import cn.eu.generate.domain.GenTable;
import cn.eu.generate.domain.GenTableColumn;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/28
 */
@Data
public class TableInfoVo {

    @NotNull(message = "表配置不能为空")
    private GenTable table;
    @NotEmpty(message = "字段配置不能为空")
    private List<GenTableColumn> columns;

}
