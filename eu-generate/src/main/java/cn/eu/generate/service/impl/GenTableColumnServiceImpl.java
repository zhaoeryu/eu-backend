package cn.eu.generate.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.generate.domain.GenTableColumn;
import cn.eu.generate.mapper.GenTableColumnMapper;
import cn.eu.generate.mapper.GenTableMapper;
import cn.eu.generate.service.IGenTableColumnService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/27
 */
@Slf4j
@Service
public class GenTableColumnServiceImpl extends EuServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService {

    @Autowired
    GenTableMapper genTableMapper;


    @Override
    public List<GenTableColumn> listByTableName(String tableName) {
        return list(new LambdaQueryWrapper<GenTableColumn>()
                .eq(GenTableColumn::getTableName, tableName)
                .orderByAsc(GenTableColumn::getColumnSort)
        );
    }
}
