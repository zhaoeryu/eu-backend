package cn.eu.system.model.query;

import cn.eu.common.utils.Query;
import lombok.Data;

/**
 * @author zhaoeryu
 * @since 2026/7/6
 */
@Data
public class SysConfigQueryCriteria {

    @Query(type = Query.Type.LIKE)
    private String configName;

    @Query(type = Query.Type.LIKE)
    private String configKey;

    @Query
    private String configType;

}
