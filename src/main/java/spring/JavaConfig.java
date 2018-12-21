package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import spring.timedjob.SchedulingMain;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/21<br/>
 * Time: 16:53<br/>
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class JavaConfig {

    @Bean
    public MethodInvokingJobDetailFactoryBean timeJobTestBean(SchedulingMain schedulingMain){
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setName("myTestJob");
        factoryBean.setGroup("my");
        factoryBean.setConcurrent(false);
        factoryBean.setTargetObject(schedulingMain);
        factoryBean.setTargetMethod("timedJob");
        return factoryBean;
    }
}
