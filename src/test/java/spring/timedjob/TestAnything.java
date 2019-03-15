package spring.timedjob;

import org.junit.Before;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void file2HashMapTest() throws IOException, ClassNotFoundException {
        HashMap<String, String> doubanLogonCookie = file2HashMap("doubanLogonCookies");
        ValueOperations valueOperations = redisTemplate.opsForValue();
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
        if (StringUtils.isEmpty(douBanService) || StringUtils.isEmpty(password)) {
            password = "i1234567";
            doubanUsername = "sail456852@hotmail.com";
        }
//        password = "i1234567";
//        doubanUsername = "wyzsailor@hotmail.com";
        System.err.println("doubanUsername = " + doubanUsername);
        System.err.println("password = " + password);
        douBanService.login(doubanUsername, password);
    }

    @org.junit.Test
    public void testDoubanComment() throws IOException, ClassNotFoundException {
        testJobDetail.timedJob2();
    }

    @org.junit.Test
    public void testRedisSetGet() {
        valueOperations.set("yuzhen", "HelloWorldIWannaBeFree");
        String yuzhen = valueOperations.get("yuzhen").toString();
        System.err.println("yuzhen = " + yuzhen);
    }
}