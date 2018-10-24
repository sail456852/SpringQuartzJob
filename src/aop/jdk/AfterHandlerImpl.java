package aop.jdk;

import java.lang.reflect.Method;

public class AfterHandlerImpl extends AfterHandler {

    @Override
    public void handlerAfter(Object proxy, Method method, Object[] args) {
        System.out.println("AfterHandlerImpl.handlerAfter");
    }
}
