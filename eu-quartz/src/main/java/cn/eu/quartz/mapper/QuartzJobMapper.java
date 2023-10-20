package cn.eu.quartz.mapper;

import cn.eu.common.base.mapper.EuBaseMapper;
import cn.eu.quartz.domain.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Mapper
public interface QuartzJobMapper extends EuBaseMapper<QuartzJob> {
}
