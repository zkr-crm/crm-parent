package cn.com.zsyk.crm.scheduler.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class CoreJobListener implements JobListener {

    private Logger logger = LogManager.getLogger(CoreJobListener.class);

    @Override
    public String getName() {
        return CoreJobListener.class.getName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
//        logger.info("jobToBeExecuted");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
//        logger.info("jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
//        logger.info("jobWasExecuted");
    }
}
