package cn.eu.system.model.query;

import cn.eu.common.utils.Query;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 操作日志 查询条件
 * @author zhaoeryu
 * @since 2023/07/11
 */
@Data
public class SysOperLogQueryCriteria {

    /**
     * 操作模块
     */
    @Query(type = Query.Type.LIKE)
    private String title;

    /**
     * 业务类型
     */
    @Query(type = Query.Type.EQ)
    private Integer businessType;

    /**
     * 操作人名称
     */
    @Query(type = Query.Type.LIKE)
    private String operName;

    /**
     * 请求URL
     */
    @Query(type = Query.Type.EQ)
    private String reqUrl;

    /**
     * 操作状态
     */
    @Query(type = Query.Type.EQ)
    private Integer status;

}
