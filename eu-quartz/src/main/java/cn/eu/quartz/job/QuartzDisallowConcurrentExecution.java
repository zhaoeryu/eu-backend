package cn.eu.quartz.job;

import cn.eu.quartz.domain.QuartzJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 * @author zhaoeryu
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, QuartzJob quartzJob) throws Exception {
        System.out.println("[串行]Hello World!" +  quartzJob.getId());
//        Thread.sleep(1000 * 3);
        super.invoke(context, quartzJob);
    }
}
