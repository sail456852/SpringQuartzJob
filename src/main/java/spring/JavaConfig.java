package spring;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import spring.timedjob.TestJobDetail;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/21<br/>
 * Time: 16:53<br/>
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@PropertySource("classpath:redis.properties")
public class JavaConfig {

    @Value("${mykey}")
    private String testValue;
    /**
     * Why are there two instances of thread pool? which makes two threads running almost
     * simultaneously
     * @param testJobDetail
     * @return
     */
    @Bean("testJobBean")
    public MethodInvokingJobDetailFactoryBean timeJobTestBean(TestJobDetail testJobDetail){
        System.err.println("JavaConfig.timeJobTestBean " + ", \"testValue\": " + testValue);
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setName("myTestJob");
        factoryBean.setGroup("my");
        factoryBean.setConcurrent(false);
        factoryBean.setTargetObject(testJobDetail);
        factoryBean.setTargetMethod("timedJob");
        return factoryBean;
    }


    @Bean("testTriggerBean")
    public CronTriggerFactoryBean timeTriggerTestBean(@Qualifier("testJobBean")
                                                              MethodInvokingJobDetailFactoryBean testJobBean) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        System.err.println("JavaConfig.timeTriggerTestBean");
        trigger.setJobDetail(testJobBean.getObject());
        trigger.setCronExpression("0/1 * * * * ?");
        trigger.setName("test-trigger");
        return trigger;
    }


    /**
     * Parameters is vargs!, added as many as you like.
     * SchedulerFactory -> Trigger -> Job -> JobDetail
     * @param testTrigger
     * @return
     */
    @Bean(name="schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("testTriggerBean") Trigger testTrigger ) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setStartupDelay(15);
        factoryBean.setTriggers(testTrigger);
        return factoryBean;
    }


}
