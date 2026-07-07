package cn.eu.system.service.impl;

import cn.eu.common.core.service.impl.EuServiceImpl;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.system.domain.SysConfig;
import cn.eu.system.mapper.SysConfigMapper;
import cn.eu.system.model.query.SysConfigQueryCriteria;
import cn.eu.system.service.ISysConfigService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2026/7/6
 */
@Service
public class SysConfigServiceImpl extends EuServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    @Autowired
    SysConfigMapper sysConfigMapper;

    @Override
    public PageResult<SysConfig> page(SysConfigQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<SysConfig> list(SysConfigQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, SysConfig.class));
    }

    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig sysConfig = getOne(new LambdaQueryWrapper<SysConfig>()
                .eq(SysConfig::getConfigKey, configKey)
                .last("LIMIT 1"));
        if (sysConfig != null) {
            return sysConfig.getConfigValue();
        }
        return null;
    }

    @Override
    public boolean checkConfigKeyUnique(SysConfig sysConfig) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getConfigKey, sysConfig.getConfigKey());
        if (sysConfig.getConfigId() != null) {
            wrapper.ne(SysConfig::getConfigId, sysConfig.getConfigId());
        }
        wrapper.last("LIMIT 1");
        return count(wrapper) == 0;
    }

}
