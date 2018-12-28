package spring.springshiro;

import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.utils.Key;

import static org.apache.shiro.session.mgt.AbstractValidatingSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/26<br/>
 * Time: 10:28<br/>
 * To change this template use File | Settings | File Templates.
 */
public class QuartzSessionValidationScheduler implements SessionValidationScheduler {

    private ValidatingSessionManager sessionManager;
//    private long sessionValidationInterval = DEFAULT_SESSION_VALIDATION_INTERVAL; // per hour
    private long sessionValidationInterval = 10; // per hour
    private static final String JOB_NAME = "SessionValidationJob";
    private static final String SESSION_MANAGER_KEY = QuartzSessionValidationJob.SESSION_MANAGER_KEY;
    private Scheduler scheduler;
    private boolean schedulerImplicitlyCreated = false;

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void enableSessionValidation() {
        System.out.println("start sessionValidationInterval = " + sessionValidationInterval + " ms ");

        try {
            SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity(JOB_NAME, Scheduler.DEFAULT_GROUP).
                    withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(sessionValidationInterval))
                    .build();

            JobDetail detail = JobBuilder.newJob(QuartzSessionValidationJob.class).withIdentity(JOB_NAME, Scheduler.DEFAULT_GROUP).build();

            detail.getJobDataMap().put(SESSION_MANAGER_KEY, this.sessionManager);
            Scheduler scheduler = getScheduler();
            scheduler.scheduleJob(detail, trigger);
            if(this.schedulerImplicitlyCreated){
                scheduler.start();
                System.err.println("QuartzSessionValidationScheduler.enableSessionValidation scheduler start successfully");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disableSessionValidation() {
        System.out.println("stop sessionValidationInterval = " + sessionValidationInterval + " ms ");
        try {
            Scheduler scheduler = getScheduler();
            if(scheduler == null) return;
            scheduler.unscheduleJob(new TriggerKey(JOB_NAME, Key.DEFAULT_GROUP));
            if(schedulerImplicitlyCreated) scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }finally {
            this.scheduler = null;
            this.schedulerImplicitlyCreated = false;
        }
    }

    public void setSessionManager(ValidatingSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setSessionValidationInterval(long sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }


    protected Scheduler getScheduler() throws SchedulerException {
        if (this.scheduler == null) {
            this.scheduler = StdSchedulerFactory.getDefaultScheduler();
            this.schedulerImplicitlyCreated = true;
        }
        return this.scheduler;
    }
}
