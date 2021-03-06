package spring.timedjob;

import org.jsoup.Connection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;
import spring.douban.DouBanService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static spring.douban.MapConvertFile.file2HashMap;
import static spring.douban.MapConvertFile.getParamMap;
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
@PropertySource(value = "classpath:redis.properties")
public class TestAnything {

    //    @Test
    public void queryBooksStatus() {
//        TestJobDetail.queryBooksStatus( "3467827");
        HashMap<String, Object> testObject = new HashMap<>();
        testObject.put("msgBody", "Java Test From Map Object");
        TestJobDetail.queryBooksStatus("2275852", testObject);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TestJobDetail testJobDetail;

    @Autowired
    private DouBanService douBanService;

    private ValueOperations valueOperations;

    @Before
    public void setup() {
        valueOperations = redisTemplate.opsForValue();
    }

    //    @org.junit.Test
    public void testFile2HashMap() throws IOException, ClassNotFoundException {
        HashMap<String, String> doubanLogonCookie = file2HashMap("doubanLogonCookies");
        ValueOperations valueOeerations = redisTemplate.opsForValue();
        String mapString = doubanLogonCookie.toString();
        mapString = mapString.substring(1, mapString.length() - 1);
        System.err.println("mapString = " + mapString);
        valueOperations.set("doubanCookie", mapString);
        Map<String, String> recreatedHashMap = string2HashMap(mapString);
        System.out.println("recreatedHashMap = " + recreatedHashMap);
    }


    @Value(value = "${doubanusername}")
    private String doubanUsername;

    @Value(value = "${password}")
    private String password;


    @org.junit.Test
    public void testDoubanLogin() throws IOException, ClassNotFoundException {
        if (StringUtils.isEmpty(doubanUsername) || StringUtils.isEmpty(password)) {
            password = "i1234567";
            doubanUsername = "sail456852@hotmail.com";
        }
        password = "Sail@456852";
        doubanUsername = "wyzsailor@hotmail.com";
        System.err.println("doubanUsername = " + doubanUsername);
        System.err.println("password = " + password);
        douBanService.login(doubanUsername, password);
    }

    @org.junit.Test
    public void testDoubanComment() throws IOException, ClassNotFoundException {
        List<String> keys = douBanService.getKeysStartWithD();
        List<String> urls = douBanService.getRedisValuesByKeys(keys);
        System.err.println("urls = " + urls);
        douBanService.callComment(false, urls); // use cookies file
    }

    @org.junit.Test
    public void testRedisSetGet() {
        valueOperations.set("yuzhen", "HelloWorldIWannaBeFree");
        String yuzhen = valueOperations.get("yuzhen").toString();
        System.err.println("yuzhen = " + yuzhen);
    }
    
    @Test
    public void testGetRedis() {
        String doubanCookie = douBanService.getRedis("doubanCookie");
        System.err.println("doubanCookie = " + doubanCookie);
    }

    @org.junit.Test
    public void testLinkedHashMapToString() throws FileNotFoundException {
        Map<String, String> paramMap = getParamMap();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(paramMap);
        System.err.println("linkedHashMap = " + linkedHashMap);
        String linkedHashMapStr = linkedHashMap.toString();
        valueOperations.set("doubanCookie", linkedHashMapStr);

        // get from douban value tested okay @author: eugene @date: 2019/3/19
        String doubanCookie = valueOperations.get("doubanCookie").toString();
        System.err.println("doubanCookie = " + doubanCookie);
        System.err.println("TestAnything.testLinkedHashMapToString trying to convert this into a map");
        Map<String, String> map = string2HashMap(doubanCookie);
        System.err.println("map = " + map);
    }

    @Test
    public void testRandomComments() {
        String s = spring.utils.StringUtils.randomCommentString();
        System.err.println("s = " + s);
    }
    
    @Test
    public void testDownloadLinkPage() {
        Connection.Response response = douBanService.downloadThisLinkWithCookies(url, Connection.Method.GET);
        System.err.println("response.body() = " + response.body());
    }

    String url = "https://www.douban.com/group/topic/139684330/";

    @Test
    public void testJoinGroup() throws IOException {
        boolean checkJoinedGroup = douBanService.checkJoinedGroup(url, Connection.Method.GET);
        if(checkJoinedGroup) {
            System.err.println("TestAnything.testJoinGroup already joined before check ");
        }else{
            boolean b = douBanService.joinGroup(url);
            if(b)
                System.err.println("TestAnything.testJoinGroup newly joined"); 
            else
                System.err.println("TestAnything.testJoinGroup newly joined failed");
        }
    }

    @Test
    public void testRedisCookie2Map() {
        Map<String, String> doubanCookie = douBanService.redisCookie2Map("doubanCookie");
        System.err.println("doubanCookie = " + doubanCookie);
    }
}