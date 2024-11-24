package cn.eu.system.service;

import cn.eu.common.base.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysOperLog;
import cn.eu.system.model.query.SysOperLogQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 操作日志 服务接口
 * @author zhaoeryu
 * @since 2023/07/11
 */
public interface ISysOperLogService extends IEuService<SysOperLog> {

    PageResult<SysOperLog> page(SysOperLogQueryCriteria criteria, Pageable pageable);

    List<SysOperLog> list(SysOperLogQueryCriteria criteria);

}
