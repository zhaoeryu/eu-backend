package cn.eu.system.model.query;

import cn.eu.common.utils.Query;
import lombok.Data;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Data
public class SysMenuQueryCriteria {

    @Query(type = Query.Type.LIKE)
    private String menuName;
    @Query
    private Integer status;

}
