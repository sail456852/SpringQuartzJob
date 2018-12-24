package spring.timedjob;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/21<br/>
 * Time: 16:49<br/>
 * To change this template use File | Settings | File Templates.
 */
@Component
@EnableScheduling
public class SchedulingMain {

    @Scheduled(cron = "0/5 * * * * ?")
    public void timedJob(){
        System.err.println("SchedulingMain.timedJob " + System.currentTimeMillis() );
    }
}
