package spring.timedjob;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/2/13<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring.xml")
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

    @org.junit.Test
    public void testRedis() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("yuzhen", "HelloWorldIWannaBeFree");
        String yuzhen = valueOperations.get("yuzhen").toString();
        System.err.println("yuzhen = " + yuzhen);
    }

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

}