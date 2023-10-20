package cn.eu.quartz.job.task;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhaoeryu
 * @since 2023/6/12
 */
@Slf4j
@Component
public class TestJob {

    @Autowired
    Scheduler scheduler;

    public void run() {
        System.out.println("run Task no params");
//        ThreadUtil.sleep(800);
    }

    public void run(String str) throws SchedulerException {
        System.out.println("run Task - " +  str + " - " + scheduler.getSchedulerName());
//        ThreadUtil.sleep(800);
    }
}
