package spring.service;

import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/2/2<br/>
 * Time: 15:54<br/>
 * To change this template use File | Settings | File Templates.
 * imagine this service implementation has not been built yet.
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String echoString(String string) {
        return string + " visited service layer";
    }
}
