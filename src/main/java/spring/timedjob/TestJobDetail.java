package spring.timedjob;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.douban.DouBan;
import spring.dto.Pay;
import spring.utils.EmailUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    private static Map<String, String> sentRecords = new HashMap<String, String>();

    private static int count = 0;

    /**
     * works fine
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Scheduled(cron = "0/2 * * * * ?") //@author: yuzhen @date: 2018/12/25  uncomment this if you wanna run
    public void timedJob2() throws IOException, ClassNotFoundException {
        Date date = new Date(); //        System.err.println("SchedulingMain.timedJob " + date);
        logger.info("timedJob() \"job start at\": " + date);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // just trying to give comment without the interfere
//        DouBan.callComment(new HashMap<>(), true); // use cookies file
        Object d1 = valueOperations.get("d1");
        if (d1 == null) {
            try {
                Thread.sleep(3000);
                System.err.println("TestJobDetail.timedJob2 is null");
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println("d1 = " + d1);
    }

    /**
     * called by trigger in Java config
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * TODO(2) change here to meet your need
     */
    public void timedJob() {
        Date date = new Date();
        System.err.println("SchedulingMain.timedJob " + date);
        String recno = "2275852";
        Map<String, Object> msgBody = new HashMap<>();
        boolean b = queryBooksStatus(recno, msgBody);
//        boolean b = true;
        if(b){
            System.err.println("sentRecords = " + sentRecords + ", \"count++\": " + count++);
            if(StringUtils.equals(sentRecords.get(recno), "1")){
                System.err.println("TestJobDetail.timedJob already sent Email, abort this action");
                return;
            }
            String msgBodyStr = msgBody.get("msgBody").toString();
            emailUtils.sendTemplateMail("sail456852@163.com", "sail456852@163.com", "java test", "pay-notshow", msgBodyStr);
            sentRecords.put(recno, "1");
        }else{
            System.err.println("TestJobDetail.timedJob not available");
        }

    }

    /**
     * query shenzhen library books
     */
    public static boolean queryBooksStatus(String recno, Map<String, Object> messageObject) {
        try {
            String url = "https://www.szlib.org.cn/Search/searchdetail.jsp?v_tablearray=bibliosm,serbibm,apabibibm,mmbibm,&v_recno=" + recno + "&v_curtable=bibliosm&site=null";
            Connection connect = Jsoup.connect(url);
            Connection method = connect.method(Connection.Method.POST).timeout(10000);
            Connection.Response response = method.execute();

            Document tempDoc = response.parse();
//            System.out.println("tempDoc = " + tempDoc);
            Elements title = tempDoc.getElementsByClass("title");
//            System.err.println(title);
            Elements infotitle = tempDoc.getElementsByClass("table_1");
//            System.out.println(infotitle);
            Element borrow = tempDoc.getElementById("borrow");
//            System.err.println(borrow);
            if (infotitle.toString().contains("在馆")){
                System.err.println("available");
                messageObject.put("msgBody", infotitle);
                return true;
            }
            return false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
