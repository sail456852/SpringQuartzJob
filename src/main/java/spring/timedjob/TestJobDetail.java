package spring.timedjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/21<br/>
 * Time: 16:49<br/>
 * To change this template use File | Settings | File Templates.
 */
@Component
@EnableScheduling
public class TestJobDetail {

    Logger logger = LoggerFactory.getLogger(TestJobDetail.class);

//    /**
//     * works fine
//     * @throws IOException
//     * @throws ClassNotFoundException
//     */
//    @Scheduled(cron = "0/1 * * * * ?") //@author: yuzhen @date: 2018/12/25  uncomment this if you wanna run
//    public void timedJob() throws IOException, ClassNotFoundException {
//        Date date = new Date(); //        System.err.println("SchedulingMain.timedJob " + date);
//        logger.info("timedJob() \"job start at\": " + date);
//        // just trying to give comment without the interfere
////        DouBan.callComment(new HashMap<>(), true); // use cookies file
//    }

    /**
     * called by trigger in Java config
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void timedJob() {
        Date date = new Date();
        System.err.println("SchedulingMain.timedJob " + date);
        logger.info("timedJob() triggered version \"job start at\": " + date);
    }
}
