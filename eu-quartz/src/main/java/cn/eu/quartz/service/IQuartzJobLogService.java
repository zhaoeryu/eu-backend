package cn.eu.quartz.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.quartz.domain.QuartzJob;
import cn.eu.quartz.domain.QuartzJobLog;
import cn.eu.quartz.service.dto.QuartzJobLogQueryCriteria;
import cn.eu.quartz.service.dto.QuartzJobQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
public interface IQuartzJobLogService extends IEuService<QuartzJobLog> {

    PageResult<QuartzJobLog> page(QuartzJobLogQueryCriteria criteria, Pageable pageable);

    List<QuartzJobLog> list(QuartzJobLogQueryCriteria criteria);

}
