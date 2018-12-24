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

    /**
     * http://localhost/aspect?asPara=testValue
     * @param asPara test param
     * @return
     * arguments passed in have been pre-retrieved by this annotation.
     * AOP class (annotated by @Aspect) acts like annotation processor
     * in this scenario. deep understanding is not required here.
     */
    @RequestMapping("/aspect")
    @ResponseBody
    @Date2LongAnnotation
    public String aspectTester(String asPara){
        System.err.println("asPara = " + asPara);
        return "ASPECT ANNOTATION Test Okay";
    }
}
