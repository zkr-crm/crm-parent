package cn.com.zsyk.crm.scheduler.core;

import cn.com.zsyk.crm.scheduler.listener.CoreJobListener;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

@Component
public class SchedulerStarter implements ApplicationRunner {
    @Autowired
    private Scheduler sched;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sched.getListenerManager().addJobListener(new CoreJobListener(), allJobs());
        sched.start();
    }
}
