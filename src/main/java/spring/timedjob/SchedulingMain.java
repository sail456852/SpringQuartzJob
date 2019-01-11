package spring.timedjob;

import msc.httpClient.douban.DouBan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    @Scheduled(cron = "0/60 * * * * ?") //@author: yuzhen @date: 2018/12/25  uncomment this if you wanna run
    public void timedJob() throws IOException {
        System.err.println("SchedulingMain.timedJob " + System.currentTimeMillis() );
        DouBan.login();
    }
}
