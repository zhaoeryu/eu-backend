package cn.eu.generate.mapper;

import cn.eu.common.base.mapper.EuBaseMapper;
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
public interface GenTableColumnMapper extends EuBaseMapper<GenTableColumn> {

}
