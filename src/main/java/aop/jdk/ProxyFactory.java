package aop.jdk;

import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    public static Object getProxy(Object targetObj, List<AbstractHandler> handlers){
        Object proxyObject = null;
        if(handlers.size() > 0 ){
            proxyObject = targetObj;
            for (int i = 0; i < handlers.size(); i++) {
                handlers.get(i).setTargetObj(proxyObject); // set the real object in
                proxyObject  = Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), handlers.get(i));
            }
            return proxyObject;
        }else{
            return targetObj;
        }
    }
}
