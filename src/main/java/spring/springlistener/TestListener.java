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
 */
@Component
public class TestListener {

    @EventListener
    @Order(1)
    @Transactional
    public void listenerHandler(TestChangeEvent event) {
        System.err.println("event = " + event);
        System.err.println("event.getSource() = " + event.getSource());
        TestSource source = (TestSource) event.getSource();
        System.err.println("source.getMsg() = " + source.getMsg());
    }
}
