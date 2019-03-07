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
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.douban.DouBanUtils;
import spring.utils.EmailUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/21<br/>
 * Time: 16:49<br/>
 * To change this template use File | Settings | File Templates.
 * Annotation
 @PropertySource(value = "classpath:redis.properties")
 can get value from property file within seconds
 */
@Component
@EnableScheduling
@PropertySource(value = "classpath:redis.properties")
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
    @Scheduled(cron = "${cronExpression}") //@author: yuzhen @date: 2018/12/25  uncomment this if you wanna run
    public void timedJob2() throws IOException, ClassNotFoundException {
        Date date = new Date(); //        System.err.println("SchedulingMain.timedJob " + date);
        logger.info("timedJob() \"job start at\": " + date);
        // just trying to give comment without the interfere
        List<String> keys = getTieziKeysRedis();
        List<String> urls = getTieziUrlsRedis(keys);
        System.err.println("urls = " + urls);
        DouBanUtils.callComment(new HashMap<>(), true, urls); // use cookies file
    }

    private List<String> getTieziUrlsRedis(List<String> keys) {
        ArrayList<String> list = new ArrayList<>();
        for (String key : keys) {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            Object value = valueOperations.get(key);
            if (value != null)
                list.add(value.toString());
        }
        return list;
    }

    private List<String> getTieziKeysRedis() {
        Set<String> redisKeys = redisTemplate.keys("d*");
        // Store the keys in a List
        List<String> keysList = new ArrayList<>();
        Iterator<String> it = redisKeys.iterator();
        while (it.hasNext()) {
            String data = it.next();
            if (!StringUtils.isEmpty(data) && data.length() <= 3)
                keysList.add(data);
        }
        for (String s : keysList) {
            System.err.println("s = " + s);
        }
        return keysList;
    }

    private void printRangeKeys(ValueOperations valueOperations) {
        for (int i = 1; i <= 5; i++) {
            Object di = valueOperations.get("d" + i);
            if (di == null) {
                try {
                    Thread.sleep(1000);
                    System.err.println("TestJobDetail.timedJob2 is null");
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("di = " + di);
        }
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
