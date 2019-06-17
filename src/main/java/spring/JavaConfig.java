package spring;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import spring.timedjob.TestJobDetail;

import java.util.Properties;

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
     * Job  Bean
     * Why are there two instances of thread pool? which makes two threads running almost
     * simultaneously Found because of listener
     * @param testJobDetail
     * @return
     */
    @Bean("testJobBean")
    public MethodInvokingJobDetailFactoryBean timeJobTestBean(TestJobDetail testJobDetail){
//        System.err.println("JavaConfig.timeJobTestBean " + ", \"testValue\": " + testValue);
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setName("myTestJob");
        factoryBean.setGroup("my");
        factoryBean.setConcurrent(false);
        factoryBean.setTargetObject(testJobDetail);
        factoryBean.setTargetMethod("timedJob");
        return factoryBean;
    }


    /**
     * Trigger Bean
     * @param testJobBean
     * @return
     */
    @Bean("testTriggerBean")
    public CronTriggerFactoryBean timeTriggerTestBean(@Qualifier("testJobBean")
                                                              MethodInvokingJobDetailFactoryBean testJobBean) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        System.err.println("JavaConfig.timeTriggerTestBean");
        trigger.setJobDetail(testJobBean.getObject());
        trigger.setCronExpression("0/5 * * * * ?"); // TODO(1) change here to meet your need
        trigger.setName("test-trigger");
        return trigger;
    }


    /**
     * METHOD 1: Trigger Config method, more complex (disabled by default)
     * Scheduler Factory Bean
     * Parameters is vargs!, added as many as you like.
     * SchedulerFactory -> Trigger -> Job -> JobDetail
     * METHOD 2: Annotation Method Mostly used Simple
     * or you just use Job -> JobDetail (with Annotation)
     * @param testTrigger
     * DISABLE THIS TRIGGER METHOD, use simple annoation
     * @return
     */
//    @Bean(name="schedulerFactory") // uncomment this bean to use method 1
//    public SchedulerFactoryBean schedulerFactory(@Qualifier("testTriggerBean") Trigger testTrigger ) {
//        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//        factoryBean.setOverwriteExistingJobs(true);
//        factoryBean.setStartupDelay(15);
//        factoryBean.setTriggers(testTrigger);
//        return factoryBean;
//    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
//        mailSender.setPort(465); // or 994 //SSL
        mailSender.setPort(25); // NON SSL

        mailSender.setUsername("sail456852@163.com");
        mailSender.setPassword("Test1991"); // not password but validation code

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
        SpringResourceTemplateResolver templateResolver
                = new SpringResourceTemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setPrefix("classpath:templates/"); // under resources folder
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

}
