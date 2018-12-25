package spring.springlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/25<br/>
 * Time: 14:38<br/>
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class EvenPublishController {

    @Autowired private ApplicationEventPublisher publisher;

    /**
     *  http://127.0.0.1/listen?desiredMsg=AKONG
     *  AKONG
     *
     * EvenPublishController.publisher
     * event = spring.springlistener.TestChangeEvent[source=spring.springlistener.TestSource@394107c3]
     * event = spring.springlistener.TestChangeEvent[source=spring.springlistener.TestSource@394107c3]
     * @param desiredMsg
     * @return
     */
    @RequestMapping("/listen")
    @ResponseBody
    public String publisher(@RequestParam(defaultValue = "helloworld") String desiredMsg){
        System.err.println("EvenPublishController.publisher");
        TestSource source = new TestSource();
        source.setMsg("HelloFromYuzhen");
        publisher.publishEvent(new TestChangeEvent(source));
        return desiredMsg;
    }
}
