package cn.eu.system.model.query;

import cn.eu.common.utils.Query;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/5/31
 */
@Data
public class SysUserQueryCriteria {

    @Query(type = Query.Type.LIKE)
    private String nickname;
    @Query
    private String mobile;
    @Query(type = Query.Type.LIKE)
    private String username;

    private Integer deptId;

    @Query(type = Query.Type.BETWEEN)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> lastActiveTime;

}
