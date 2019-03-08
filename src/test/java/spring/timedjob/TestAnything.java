package spring.timedjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;
import spring.douban.DouBanService;

import java.io.IOException;
import java.util.*;

import static spring.douban.MapConvertFile.file2HashMap;
import static spring.douban.MapConvertFile.string2HashMap;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/2/13<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring.xml")
@WebAppConfiguration
public class TestAnything {

    @Test
    public void queryBooksStatus() {
//        TestJobDetail.queryBooksStatus( "3467827");
        HashMap<String, Object> testObject = new HashMap<>();
        testObject.put("msgBody", "Java Test From Map Object");
        TestJobDetail.queryBooksStatus( "2275852", testObject);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DouBanService douBanService;

//    private ValueOperations valueOperations;
//
//    @Before
//    public void setup() {
//        valueOperations = redisTemplate.opsForValue();
//    }

//    @org.junit.Test
//    public void testRedis() {
//        valueOperations.set("yuzhen", "HelloWorldIWannaBeFree");
//        String yuzhen = valueOperations.get("yuzhen").toString();
//        System.err.println("yuzhen = " + yuzhen);
//    }

    @org.junit.Test
    public void testRedisScan() {
        Set<String> redisKeys = redisTemplate.keys("d*");
        // Store the keys in a List
        List<String> keysList = new ArrayList<>();
        Iterator<String> it = redisKeys.iterator();
        while (it.hasNext()) {
            String data = it.next();
            if(!StringUtils.isEmpty(data) && data.length() <= 3)
            keysList.add(data);
        }

        for (String s : keysList) {
            System.err.println("s = " + s);
        }
    }

    @org.junit.Test
    public void file2HashMapTest() throws IOException, ClassNotFoundException {
        HashMap<String, String> doubanLogonCookie = file2HashMap("doubanLogonCookies");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String mapString = doubanLogonCookie.toString();
        mapString = mapString.substring(1, mapString.length() - 1);
        System.err.println("mapString = " + mapString);
        valueOperations.set("doubanCookie", mapString );
        Map<String, String> recreatedHashMap = string2HashMap(mapString);
        System.out.println("recreatedHashMap = " + recreatedHashMap);
    }
    
    @Test
    public void doubanTest() throws IOException, ClassNotFoundException {
        List<String> keys = douBanService.getTieziKeysRedis();
        List<String> urls = douBanService.getTieziUrlsRedis(keys);
        System.err.println("urls = " + urls);
        douBanService.callComment(true, urls); // use cookies file
    }

}