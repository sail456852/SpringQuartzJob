package aop.jdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * the 3rd layer under abstract layer
 */
public abstract class BeforeHandler extends AbstractHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        handlerBefore(proxy,method, args);
        Object result = method.invoke(getTargetObj(), args); // pass the object back
        return result;
    }

    public abstract void handlerBefore(Object proxy, Method method, Object[] args);
}
