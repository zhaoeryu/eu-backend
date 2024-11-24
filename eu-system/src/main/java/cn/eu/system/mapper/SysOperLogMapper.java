package cn.eu.system.mapper;

import cn.eu.common.base.mapper.EuBaseMapper;
import cn.eu.system.domain.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志 Mapper
 * @author zhaoeryu
 * @since 2023/07/11
 */
@Mapper
public interface SysOperLogMapper extends EuBaseMapper<SysOperLog> {

}
