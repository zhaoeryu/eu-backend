package cn.eu.quartz.service;

import cn.eu.common.core.service.IEuService;
import cn.eu.common.model.PageResult;
import cn.eu.quartz.domain.QuartzJob;
import cn.eu.quartz.service.dto.QuartzJobQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
public interface IQuartzJobService extends IEuService<QuartzJob> {

    PageResult<QuartzJob> page(QuartzJobQueryCriteria criteria, Pageable pageable);

    List<QuartzJob> list(QuartzJobQueryCriteria criteria);
    
    /**
     * 暂停或者恢复任务
     */
    void pauseOrResume(QuartzJob quartzJob);

    void exec(String jobId);

    void deleteJob(String jobId);

    void deleteJobs(List<String> jobIds);

    void updateJob(QuartzJob entity);

    void saveJob(QuartzJob entity);
}
