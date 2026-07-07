package cn.eu.system.service;

import cn.eu.common.core.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.system.domain.SysConfig;
import cn.eu.system.model.query.SysConfigQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2026/7/6
 */
public interface ISysConfigService extends IEuService<SysConfig> {

    PageResult<SysConfig> page(SysConfigQueryCriteria criteria, Pageable pageable);

    List<SysConfig> list(SysConfigQueryCriteria criteria);

    /**
     * 根据参数键名查询参数值
     * @param configKey 参数键名
     * @return 参数值
     */
    String selectConfigByKey(String configKey);

    /**
     * 校验参数键名是否唯一
     * @param sysConfig 参数配置信息
     * @return true 唯一，false 不唯一
     */
    boolean checkConfigKeyUnique(SysConfig sysConfig);

}
