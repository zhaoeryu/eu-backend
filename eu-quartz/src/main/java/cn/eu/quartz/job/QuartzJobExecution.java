package cn.eu.quartz.job;

import cn.eu.quartz.domain.QuartzJob;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 * @author zhaoeryu
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, QuartzJob quartzJob) throws Exception {
        System.out.println("[并发]Hello World!" +  quartzJob.getId());
//        Thread.sleep(1000 * 3);

        super.invoke(context, quartzJob);
    }
}
