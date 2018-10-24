package aop.jdk;

import java.lang.reflect.InvocationHandler;

/**
 * For saving the actual object being proxied
 * in the reflection package
 * abstract class can delay the implementation the having to implement method
 * 1. for giving the sub classes each targetObj container!
 */
public  abstract class AbstractHandler implements InvocationHandler {

    private Object targetObj;

    public Object getTargetObj() {
        return targetObj;
    }

    public void setTargetObj(Object targetObj) {
        this.targetObj = targetObj;
    }
}
