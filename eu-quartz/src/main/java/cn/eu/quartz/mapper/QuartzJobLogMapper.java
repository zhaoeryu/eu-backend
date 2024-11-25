package cn.eu.quartz.mapper;

import cn.eu.common.core.mapper.EuBaseMapper;
import cn.eu.quartz.domain.QuartzJobLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Mapper
public interface QuartzJobLogMapper extends EuBaseMapper<QuartzJobLog> {
}
