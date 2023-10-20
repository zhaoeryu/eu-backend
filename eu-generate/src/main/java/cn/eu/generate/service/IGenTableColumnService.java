package cn.eu.generate.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.generate.domain.GenTableColumn;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
public interface IGenTableColumnService extends IEuService<GenTableColumn> {

    List<GenTableColumn> listByTableName(String tableName);
}
