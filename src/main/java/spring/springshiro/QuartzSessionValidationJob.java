package spring.springshiro;

import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/28<br/>
 * Time: 18:55<br/>
 * To change this template use File | Settings | File Templates.
 */
public class QuartzSessionValidationJob implements Job {
    public static final String SESSION_MANAGER_KEY = "sessionManager";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        ValidatingSessionManager sessionManager = (ValidatingSessionManager)mergedJobDataMap.get(SESSION_MANAGER_KEY);
        System.err.println("QuartzSessionValidationJob.execute start");
        sessionManager.validateSessions();
        System.err.println("QuartzSessionValidationJob.execute stop");
    }
}
