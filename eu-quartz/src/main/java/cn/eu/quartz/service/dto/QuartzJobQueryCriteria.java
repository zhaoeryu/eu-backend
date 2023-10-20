package cn.eu.quartz.service.dto;

import cn.eu.common.utils.Query;
import lombok.Data;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Data
public class QuartzJobQueryCriteria {

    @Query(type = Query.Type.LIKE)
    private String jobName;

}
