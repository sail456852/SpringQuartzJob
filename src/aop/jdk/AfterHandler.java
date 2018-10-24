package aop.jdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AfterHandler extends AbstractHandler{


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        handlerAfter(proxy,method, args);
        Object result =  method.invoke(getTargetObj(), args); // pass the object back
        return result;
    }

    protected abstract void handlerAfter(Object proxy, Method method, Object[] args);

}
