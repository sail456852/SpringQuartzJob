package spring.springaop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Administrator<br/>
 * Date: 2018/11/21<br/>
 * Time: 15:05<br/>
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class Date2LongController {

    @RequestMapping("/aspect")
    @ResponseBody
    @Date2LongAnnotation
    public String aspectTester(){
        System.err.println("Date2LongController.aspectTester");
        return "ASPECT ANNOTATION Test Okay";
    }
}
