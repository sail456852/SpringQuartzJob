package spring.springlistener;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/25<br/>
 * Time: 14:26<br/>
 * To change this template use File | Settings | File Templates.
 * Order can't determine in which order the methods are called.
 * Order annotation with low value, can't determine the order of execution
 */
@Component
public class TestListener {

    @EventListener
    @Order(10)
    @Transactional
    public void listenerSecondHandler(TestChangeEvent event) {
        System.out.println("TestListener.listenerSecondHandler");
        TestSource source = (TestSource) event.getSource();
        System.out.println("source.getMsg() = " + source.getMsg());
    }

    @EventListener
    @Order(100)
    @Transactional
    public void listenerHandler(TestChangeEvent event) {
        System.err.println("event = " + event);
        System.err.println("event.getSource() = " + event.getSource());
        TestSource source = (TestSource) event.getSource();
        System.err.println("source.getMsg() = " + source.getMsg());
    }
}
