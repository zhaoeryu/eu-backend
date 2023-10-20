package cn.eu.system.service.impl;

import cn.eu.common.base.service.impl.EuServiceImpl;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.system.domain.SysOperLog;
import cn.eu.system.mapper.SysOperLogMapper;
import cn.eu.system.model.query.SysOperLogQueryCriteria;
import cn.eu.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务实现类
 * @author zhaoeryu
 * @since 2023/07/11
 */
@Service
public class SysOperLogServiceImpl extends EuServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {

    @Autowired
    SysOperLogMapper sysOperLogMapper;

    @Override
    public PageResult<SysOperLog> page(SysOperLogQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysOperLog> list(SysOperLogQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, SysOperLog.class));
    }
    
}
