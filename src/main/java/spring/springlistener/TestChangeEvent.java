package spring.springlistener;

import org.springframework.context.ApplicationEvent;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/25<br/>
 * Time: 14:28<br/>
 * To change this template use File | Settings | File Templates.
 */
public class TestChangeEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TestChangeEvent(TestSource source) {
        super(source);
    }
}
