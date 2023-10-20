package cn.eu.quartz.job;

import cn.eu.common.utils.SpringContextHolder;
import cn.eu.message.enums.MailSendType;
import cn.eu.message.handler.dispatcher.MessageDispatcher;
import cn.eu.message.model.Mail;
import cn.eu.quartz.QuartzConstants;
import cn.eu.quartz.domain.QuartzJob;
import cn.eu.quartz.domain.QuartzJobLog;
import cn.eu.quartz.enums.QuartzJobStatus;
import cn.eu.quartz.service.IQuartzJobLogService;
import cn.eu.quartz.service.IQuartzJobService;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author zhaoeryu
 * @since 2023/6/13
 */
@Slf4j
public abstract class AbstractQuartzJob extends QuartzJobBean {

    private static final ThreadLocal<LocalDateTime> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//    public void execute(JobExecutionContext context) throws JobExecutionException {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzConstants.JOB_DATA_KEY);
        log.info("--------------------------------------");
        log.info("[{}]任务开始执行", quartzJob.getJobName());
        try {
            before(context, quartzJob);
            doExecute(context, quartzJob);
            after(context, quartzJob, null);
        } catch (Exception e) {
            log.error("任务执行异常: " + e.getMessage(), e);
            after(context, quartzJob, e);
        }
        log.info("[{}]任务执行结束", quartzJob.getJobName());
    }

    protected void before(JobExecutionContext context, QuartzJob quartzJob) {
        THREAD_LOCAL.set(LocalDateTime.now());
    }

    protected void after(JobExecutionContext context, QuartzJob quartzJob, Exception e) {
        LocalDateTime startTime = THREAD_LOCAL.get();
        THREAD_LOCAL.remove();
        LocalDateTime endTime = LocalDateTime.now();
        long execMs = Duration.between(startTime, endTime).toMillis();

        log.info("任务名称 = [{}]，任务组名 = [{}]，执行耗时 = [{}]毫秒",
                quartzJob.getJobName(), quartzJob.getJobGroup(), execMs);

        IQuartzJobService quartzJobService = SpringContextHolder.getBean(IQuartzJobService.class);

        QuartzJobLog quartzJobLog = new QuartzJobLog();
        quartzJobLog.setJobId(quartzJob.getId());
        quartzJobLog.setJobName(quartzJob.getJobName());
        quartzJobLog.setSpringBeanName(quartzJob.getSpringBeanName());
        quartzJobLog.setMethodName(quartzJob.getMethodName());
        quartzJobLog.setMethodParams(quartzJob.getMethodParams());
        quartzJobLog.setSuccess(e == null);
        if (e != null) {
            quartzJobLog.setExceptionMessage(e.getMessage());
            quartzJobLog.setExceptionDetail(ExceptionUtil.stacktraceToString(e));

            // 如果失败了需要暂停
            if (quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()) {
                quartzJob.setStatus(QuartzJobStatus.PAUSE.getValue());
                quartzJobService.pauseOrResume(quartzJob);
            }

            // 如果配置了邮箱，任务失败发送邮件
            if (StrUtil.isNotBlank(quartzJob.getAlarmEmail())) {
                try {
                    // send email
                    MessageDispatcher dispatcher = SpringContextHolder.getBean(MessageDispatcher.class);
                    Mail mail = new Mail();
                    mail.setTo(quartzJob.getAlarmEmail());
                    mail.setSubject("任务执行失败 - [" + quartzJob.getJobName() + "]");
                    mail.setContent(quartzJobLog.getExceptionDetail());
                    mail.setSendType(MailSendType.TEXT);
                    dispatcher.dispatch(mail);
                } catch (Exception emailEx) {
                    log.error("发送邮件失败：" + emailEx.getMessage(), emailEx);
                }
            }
        }
        quartzJobLog.setStartTime(startTime);
        quartzJobLog.setEndTime(endTime);
        quartzJobLog.setExecTime(execMs);

        // save log to database
        IQuartzJobLogService quartzJobLogService = SpringContextHolder.getBean(IQuartzJobLogService.class);
        quartzJobLogService.save(quartzJobLog);
    }

    protected abstract void doExecute(JobExecutionContext context, QuartzJob quartzJob) throws Exception;

    /**
     * 执行任务
     */
    protected void invoke(JobExecutionContext context, QuartzJob quartzJob) {
        Object instance = null;
        if (quartzJob.getInvokeClassName() != null) {
            instance = ReflectUtil.newInstance(quartzJob.getInvokeClassName());
        } else if (quartzJob.getSpringBeanName() != null) {
            boolean isBean = SpringContextHolder.getApplicationContext().containsBean(quartzJob.getSpringBeanName());
            Assert.isTrue(isBean, "SpringBeanName不存在：" + quartzJob.getSpringBeanName());
            instance = SpringContextHolder.getBean(quartzJob.getSpringBeanName());
        } else {
            throw new RuntimeException("未配置任务执行类");
        }

        Assert.hasText(quartzJob.getMethodName(), "任务方法名不能为空");
        if (StrUtil.isBlank(quartzJob.getMethodParams())) {
            ReflectUtil.invoke(instance, quartzJob.getMethodName());
        } else {
            ReflectUtil.invoke(instance, quartzJob.getMethodName(), quartzJob.getMethodParams());
        }
    }
}
