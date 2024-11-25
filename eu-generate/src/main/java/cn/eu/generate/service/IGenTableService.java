package cn.eu.generate.service;

import cn.eu.common.core.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.generate.domain.GenTable;
import cn.eu.generate.domain.GenTableColumn;
import cn.eu.generate.model.query.GenTableQueryCriteria;
import cn.eu.generate.model.dto.GenerateTemplateDto;
import cn.eu.generate.model.vo.TableInfoVo;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
public interface IGenTableService extends IEuService<GenTable> {

    PageResult<GenTable> listTables(GenTableQueryCriteria criteria, Pageable pageable);

    List<GenerateTemplateDto> preview(String tableName);

    GenTable getGenTable(String tableName);

    List<GenTableColumn> getGenTableColumns(String tableName);

    TableInfoVo getTableInfo(String tableName);

    boolean saveTable(GenTable entity);

    void saveColumns(List<GenTableColumn> entityList);

    void sync(String tableName);

    void generator(String tableName, HttpServletResponse response) throws IOException;
}
