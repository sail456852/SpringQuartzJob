package spring.timedjob;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

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

}