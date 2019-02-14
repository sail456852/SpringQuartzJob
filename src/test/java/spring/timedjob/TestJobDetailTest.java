package spring.timedjob;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/2/13<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */

public class TestJobDetailTest {

    @Test
    public void queryBooksStatus() {
//        TestJobDetail.queryBooksStatus( "3467827");
        HashMap<String, Object> testObject = new HashMap<>();
        testObject.put("msgBody", "Java Test From Map Object");
        TestJobDetail.queryBooksStatus( "2275852", testObject);
    }
}