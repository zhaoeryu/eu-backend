package cn.eu.system.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.system.domain.SysNotice;
import cn.eu.system.mapper.SysNoticeMapper;
import cn.eu.system.model.query.SysNoticeQueryCriteria;
import cn.eu.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知公告 服务实现类
 * @author Eu.z
 * @since 2023/08/30
 */
@Service
public class SysNoticeServiceImpl extends EuServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {

    @Autowired
    SysNoticeMapper sysNoticeMapper;

    @Override
    public PageResult<SysNotice> page(SysNoticeQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysNotice> list(SysNoticeQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, SysNotice.class));
    }
    
}
