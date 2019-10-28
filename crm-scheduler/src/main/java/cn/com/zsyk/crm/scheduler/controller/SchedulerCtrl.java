package cn.com.zsyk.crm.scheduler.controller;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zsyk.crm.common.dto.Result;
import cn.com.zsyk.crm.scheduler.Constants;
import cn.com.zsyk.crm.scheduler.core.CoreJob;

@RestController
public class SchedulerCtrl implements InitializingBean {

    @Autowired
    private Scheduler sched;

    /**
     * @api {post} /crm/scheduler/job 新增作业
     * @apiName addJob
     * @apiGroup Scheduler
     * @apiParam {String} jobId 作业id
     * @apiParam {String} jobDescription 作业描述
     * @apiParam {String} jobGroup 所属组
     * @apiParam {String} cron cron表达式
     * @apiParam {String} jobDescription 作业描述
     * @apiParam {String} targetUrl 目标url
     * @apiParam {String} params 参数（json格式）
     */
    @SuppressWarnings("rawtypes")
	@PostMapping(path = "/crm/scheduler/job")
    public Result addJob(HttpServletRequest req) throws Exception {
        String jobId = req.getParameter(Constants.JOB_ID);
        String jobDescription = req.getParameter(Constants.JOB_DESCRIPTION);
        String jobGroup = req.getParameter(Constants.JOB_GROUP);
        String cron = req.getParameter(Constants.CRON);
        String targetUrl = req.getParameter(Constants.TARGET_URL);
        String params = req.getParameter(Constants.PARAMS);

        JobDetail job = newJob(CoreJob.class)
                .withDescription(jobDescription)
                .withIdentity(jobId, jobGroup)
                .usingJobData(Constants.TARGET_URL, targetUrl)
                .usingJobData(Constants.PARAMS, params)
                .build();


        CronTrigger trigger = newTrigger()
                .withIdentity(jobId, jobGroup)
                .withSchedule(cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
                .build();

        sched.scheduleJob(job, trigger);

        return new Result();
    }


    /**
     * @api {delete} /crm/scheduler/job 删除作业
     * @apiName deleteJob
     * @apiGroup Scheduler
     * @apiParam {String} jobId 作业id
     * @apiParam {String} jobGroup 所属组
     */
    @DeleteMapping(path = "/crm/scheduler/job")
    public Result deleteJob(HttpServletRequest req) throws Exception {
        String jobId = req.getParameter(Constants.JOB_ID);
        String jobGroup = req.getParameter(Constants.JOB_GROUP);

        JobKey jobKey = new JobKey(jobId, jobGroup);
        sched.deleteJob(jobKey);

        return new Result();
    }

    /**
     * @api {put} /crm/scheduler/job/pause 暂停作业
     * @apiName pauseJob
     * @apiGroup Scheduler
     * @apiParam {String} jobId 作业id
     * @apiParam {String} jobGroup 所属组
     */
    @PutMapping(path = "/crm/scheduler/job/pause")
    public Result pauseJob(HttpServletRequest req) throws Exception {
        String jobId = req.getParameter(Constants.JOB_ID);
        String jobGroup = req.getParameter(Constants.JOB_GROUP);

        JobKey jobKey = new JobKey(jobId, jobGroup);
        sched.pauseJob(jobKey);

        return new Result();
    }

    /**
     * @api {put} /crm/scheduler/job/resume 恢复作业
     * @apiName resumeJob
     * @apiGroup Scheduler
     * @apiParam {String} jobId 作业id
     * @apiParam {String} jobGroup 所属组
     */
    @PutMapping(path = "/crm/scheduler/job/resume")
    public Result resumeJob(HttpServletRequest req) throws Exception {
        String jobId = req.getParameter(Constants.JOB_ID);
        String jobGroup = req.getParameter(Constants.JOB_GROUP);

        JobKey jobKey = new JobKey(jobId, jobGroup);
        sched.resumeJob(jobKey);
        return new Result();
    }


    @GetMapping(path = "/crm/scheduler/job")
    public Result queryJobs(HttpServletRequest req) throws Exception {
        List jobs = new ArrayList();
        Result result = new Result();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = sched.getJobKeys(matcher);
        jobKeys.stream().forEach((JobKey jk) -> {
            try {
                Map job = new HashMap();
                JobDetail jd = sched.getJobDetail(jk);
                List<? extends Trigger> triggers = sched.getTriggersOfJob(jk);
                CronTrigger trigger = (CronTrigger) triggers.get(0);

                job.put("desc", jd.getDescription());
                job.put("name", jk.getName());
                job.put("group", jk.getGroup());
                job.put("cron", trigger.getCronExpression());
                job.put("previousFireTime", trigger.getPreviousFireTime());
                job.put("nextFireTime", trigger.getNextFireTime());
                job.put("status", sched.getTriggerState(trigger.getKey()));

//                String state = null;
//                Connection conn = DBConnectionManager.getInstance().getConnection("DS");
//                PreparedStatement ps = null;
//                ResultSet rs = null;
//                try {
//                    ps = conn.prepareStatement(Util.rtp(StdJDBCConstants.SELECT_TRIGGER_STATE, "SC_", "'crmScheduler'"));
//                    ps.setString(1, trigger.getKey().getName());
//                    ps.setString(2, trigger.getKey().getGroup());
//                    rs = ps.executeQuery();
//                    if (rs.next()) {
//                        state = rs.getString("TRIGGER_STATE");
//                    } else {
//                        state = "DELETED";
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    rs.close();
//                    ps.close();
//                }
//                job.put("status", state.intern());

                jobs.add(job);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        result.setData(jobs);
        return result;
    }

    @GetMapping(path = "/crm/scheduler/status")
    public Result queryStatus(HttpServletRequest req) throws Exception {
        Result result = new Result();
        result.setData(sched.isInStandbyMode());
        return result;
    }

    @PutMapping(path = "/crm/scheduler/status/standby")
    public Result standby(HttpServletRequest req) throws Exception {
        sched.standby();
        Result result = new Result();
        return result;
    }

    @PutMapping(path = "/crm/scheduler/status/start")
    public Result start(HttpServletRequest req) throws Exception {
        sched.start();
        Result result = new Result();
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        sched.
    }
}
