package cn.eu.system.model.query;

import cn.eu.common.utils.Query;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 通知公告 查询条件
 * @author Eu.z
 * @since 2023/08/30
 */
@Data
public class SysNoticeQueryCriteria {

    /**
     * 标题
     */
    @Query(type = Query.Type.LIKE)
    private String title;

    /**
     * 公告类型
     */
    @Query(type = Query.Type.EQ)
    private Integer type;

    /**
     * 公告状态
     */
    @Query(type = Query.Type.EQ)
    private Integer status;

}
