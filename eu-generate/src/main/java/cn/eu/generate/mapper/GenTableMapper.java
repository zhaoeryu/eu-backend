package cn.eu.generate.mapper;

import cn.eu.common.core.mapper.EuBaseMapper;
import cn.eu.generate.domain.GenTable;
import cn.eu.generate.domain.GenTableColumn;
import cn.eu.generate.model.query.GenTableQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
@Mapper
public interface GenTableMapper extends EuBaseMapper<GenTable> {

    List<GenTable> selectDbTableList(GenTableQueryCriteria criteria);
    GenTable selectDbTableByTableName(@Param("tableName") String tableName);
    List<GenTableColumn> selectDbTableColumnList(@Param("tableName") String tableName);

}
