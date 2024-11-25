package cn.eu.quartz.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.quartz.domain.QuartzJobLog;
import cn.eu.quartz.mapper.QuartzJobLogMapper;
import cn.eu.quartz.service.IQuartzJobLogService;
import cn.eu.quartz.service.dto.QuartzJobLogQueryCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Slf4j
@Service
public class QuartzJobLogServiceImpl extends EuServiceImpl<QuartzJobLogMapper, QuartzJobLog> implements IQuartzJobLogService {
    @Override
    public PageResult<QuartzJobLog> page(QuartzJobLogQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<QuartzJobLog> list(QuartzJobLogQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, QuartzJobLog.class));
    }
}
