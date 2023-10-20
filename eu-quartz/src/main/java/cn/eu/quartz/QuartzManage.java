package cn.eu.quartz;

import cn.eu.quartz.domain.QuartzJob;
import cn.eu.quartz.enums.QuartzJobConcurrent;
import cn.eu.quartz.enums.QuartzJobMisfirePolicy;
import cn.eu.quartz.enums.QuartzJobStatus;
import cn.eu.quartz.job.QuartzDisallowConcurrentExecution;
import cn.eu.quartz.job.QuartzJobExecution;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Date;

/**
 * @author zhaoeryu
 * @since 2023/6/13
 */
@Slf4j
@Component
public class QuartzManage {

    @Autowired
    Scheduler scheduler;

    /**
     * 清空所有任务
     */
    public void clear() {
        try {
            scheduler.clear();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 添加任务
     */
    public void addJob(QuartzJob quartzJob) throws Exception {
        // 校验cron表达式
        Assert.isTrue(CronExpression.isValidExpression(quartzJob.getCron()), "cron表达式错误");

        // 获取Job执行类
        Class<? extends Job> jobClazz = QuartzJobConcurrent.ENABLED.getValue() == quartzJob.getConcurrent()
                ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;

        // 构建Job
        JobKey jobKey = getJobKey(quartzJob);
        JobDetail jobDetail = JobBuilder.newJob(jobClazz).withIdentity(jobKey).build();

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCron());
        // 执行策略
        cronScheduleBuilder = handleCronScheduleMisfirePolicy(quartzJob, cronScheduleBuilder);

        // 构建触发器
        TriggerKey triggerKey = getTriggerKey(quartzJob);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder)
                .build();

        // 放入参数
        jobDetail.getJobDataMap().put(QuartzConstants.JOB_DATA_KEY, quartzJob);

        // 判断是否存在
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }

        // 判断是否过期, 过期则不装配任务
        if (getNextValidTimeAfter(quartzJob.getCron()) != null) {
            // 装配调度任务
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }

        // 暂停任务
        if (QuartzJobStatus.PAUSE.getValue() == quartzJob.getStatus()) {
            scheduler.pauseJob(jobKey);
        }
    }

    /**
     * 更新任务
     */
    public void updateJob(QuartzJob quartzJob){
        try {
            JobKey jobKey = getJobKey(quartzJob);
            // 判断是否存在， 如果存在先删除再创建
            if (scheduler.checkExists(jobKey)) {
                deleteJob(quartzJob);
            }
            addJob(quartzJob);
        } catch (Exception e){
            log.error("更新定时任务失败", e);
            throw new RuntimeException("更新定时任务失败");
        }

    }

    /**
     * 暂停任务
     */
    public void pauseJob(QuartzJob quartzJob){
        try {
            scheduler.pauseJob(getJobKey(quartzJob));
        } catch (Exception e){
            log.error("定时任务暂停失败", e);
            throw new RuntimeException("定时任务暂停失败");
        }
    }

    /**
     * 立即执行任务
     */
    public void runJobNow(QuartzJob quartzJob){
        try {
            JobKey jobKey = getJobKey(quartzJob);
            if (!scheduler.checkExists(jobKey)) {
                addJob(quartzJob);
            }
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(QuartzConstants.JOB_DATA_KEY, quartzJob);
            scheduler.triggerJob(jobKey,dataMap);
        } catch (Exception e){
            log.error("定时任务执行失败", e);
            throw new RuntimeException("定时任务执行失败");
        }
    }

    /**
     * 恢复执行任务
     */
    public void resumeJob(QuartzJob quartzJob){
        try {
            JobKey jobKey = getJobKey(quartzJob);
            if (!scheduler.checkExists(jobKey)) {
                addJob(quartzJob);
            }
            scheduler.resumeJob(jobKey);
        } catch (Exception e){
            log.error("恢复定时任务失败", e);
            throw new RuntimeException("恢复定时任务失败");
        }
    }

    /**
     * 删除任务
     */
    public void deleteJob(QuartzJob quartzJob){
        try {
            JobKey jobKey = getJobKey(quartzJob);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (Exception e){
            log.error("删除定时任务失败", e);
            throw new RuntimeException("删除定时任务失败");
        }
    }

    public static JobKey getJobKey(QuartzJob quartzJob) {
        return JobKey.jobKey(QuartzConstants.JOB_NAME + quartzJob.getId(), quartzJob.getJobGroup());
    }
    public static TriggerKey getTriggerKey(QuartzJob quartzJob) {
        return TriggerKey.triggerKey(QuartzConstants.JOB_NAME + quartzJob.getId(), quartzJob.getJobGroup());
    }

    /**
     * 处理cronScheduleBuilder的执行策略
     * @param quartzJob 任务
     * @param cb cronScheduleBuilder
     * @return cronScheduleBuilder
     */
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(QuartzJob quartzJob, CronScheduleBuilder cb) {
        QuartzJobMisfirePolicy misfirePolicy = QuartzJobMisfirePolicy.getByValue(quartzJob.getMisfirePolicy());
        Assert.notNull(misfirePolicy, "The misfire policy '" + quartzJob.getMisfirePolicy() + "' cannot be recognized.");
        switch (misfirePolicy) {
            case DEFAULT:
                return cb;
            case IGNORE_MISFIRES:
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case FIRE_AND_PROCEED:
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case DO_NOTHING:
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw new IllegalArgumentException("The misfire policy '" + quartzJob.getMisfirePolicy() + "' cannot be recognized.");
        }
    }

    /**
     * 获取下次执行时间
     * @param cron cron表达式
     * @return 下次执行时间
     * @throws ParseException 解析异常
     */
    public static Date getNextValidTimeAfter(String cron) throws ParseException {
        CronExpression cronExpression = new CronExpression(cron);
        return cronExpression.getNextValidTimeAfter(new Date());
    }
}
