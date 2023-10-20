package cn.eu.quartz.service.impl;

import cn.eu.common.base.service.impl.EuServiceImpl;
import cn.eu.common.model.PageResult;
import cn.eu.common.utils.MpQueryHelper;
import cn.eu.quartz.QuartzManage;
import cn.eu.quartz.domain.QuartzJob;
import cn.eu.quartz.enums.QuartzJobStatus;
import cn.eu.quartz.mapper.QuartzJobMapper;
import cn.eu.quartz.service.IQuartzJobService;
import cn.eu.quartz.service.dto.QuartzJobQueryCriteria;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author zhaoeryu
 * @since 2023/6/24
 */
@Slf4j
@Service
public class QuartzJobServiceImpl extends EuServiceImpl<QuartzJobMapper, QuartzJob> implements IQuartzJobService {

    @Autowired
    QuartzManage quartzManage;

    @PostConstruct
    public void init() throws Exception {
        log.info("开始注入定时任务");
        quartzManage.clear();

        // 读取数据库里的Job任务，添加到scheduler里
        List<QuartzJob> jobList = list();

        for (QuartzJob job : jobList) {
            quartzManage.addJob(job);
        }

        log.info("定时任务注入完成");
    }

    @Override
    public PageResult<QuartzJob> page(QuartzJobQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        return PageResult.of(list(criteria));
    }

    @Override
    public List<QuartzJob> list(QuartzJobQueryCriteria criteria) {
        return list(MpQueryHelper.buildQueryWrapper(criteria, QuartzJob.class));
    }

    @Override
    public void pauseOrResume(QuartzJob quartzJob) {
        if (QuartzJobStatus.PAUSE.getValue() == quartzJob.getStatus()) {
            // 暂停任务
            quartzManage.pauseJob(quartzJob);
        } else if (QuartzJobStatus.NORMAL.getValue() == quartzJob.getStatus()) {
            // 恢复任务
            quartzManage.resumeJob(quartzJob);
        }
        update(new LambdaUpdateWrapper<QuartzJob>()
            .eq(QuartzJob::getId, quartzJob.getId())
            .set(QuartzJob::getStatus, quartzJob.getStatus())
        );
    }

    @Override
    public void exec(String jobId) {
        QuartzJob quartzJob = getById(jobId);
        quartzManage.runJobNow(quartzJob);
    }

    @Override
    public void deleteJob(String jobId) {
        QuartzJob quartzJob = getById(jobId);
        quartzManage.deleteJob(quartzJob);
        removeById(jobId);
    }

    @Override
    public void deleteJobs(List<String> jobIds) {
        List<QuartzJob> quartzJobs = listByIds(jobIds);
        quartzJobs.forEach(quartzManage::deleteJob);
        removeByIds(jobIds);
    }

    @Override
    public void updateJob(QuartzJob entity) {
        quartzManage.updateJob(entity);
        updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveJob(QuartzJob entity) {
        save(entity);
        try {
            quartzManage.addJob(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
