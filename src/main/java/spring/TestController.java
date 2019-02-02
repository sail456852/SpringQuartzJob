package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.service.TestService;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/21<br/>
 * Time: 14:50<br/>
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class TestController {

    @Value("${mykey}")
    private String testValue;

    @Autowired
    private TestService testService;

    /**
     * @return
     * http://localhost:11111/test
     */
    @RequestMapping("/test")
    @ResponseBody
    public String testController(){
        System.err.println(" TestController testValue = " + testValue);
        System.err.println("TestController.testController");
        String testControllerString = testService.echoString("testControllerString");
        System.err.println("testControllerString = " + testControllerString);
        return "Test Okay";
    }

}
