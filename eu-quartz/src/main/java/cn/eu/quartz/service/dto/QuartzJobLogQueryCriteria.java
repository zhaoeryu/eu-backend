package cn.eu.quartz.service.dto;

import cn.eu.common.utils.Query;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Data
public class QuartzJobLogQueryCriteria {

    @Query
    private String jobId;

    @Query(type = Query.Type.GT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @Query(type = Query.Type.LT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
