package cn.eu.system.service;

import cn.eu.common.core.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysNotice;
import cn.eu.system.model.query.SysNoticeQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 通知公告 服务接口
 * @author Eu.z
 * @since 2023/08/30
 */
public interface ISysNoticeService extends IEuService<SysNotice> {

    PageResult<SysNotice> page(SysNoticeQueryCriteria criteria, Pageable pageable);

    List<SysNotice> list(SysNoticeQueryCriteria criteria);

}
