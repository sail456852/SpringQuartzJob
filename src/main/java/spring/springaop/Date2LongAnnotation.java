package spring.springaop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Administrator<br/>
 * Date: 2018/11/21<br/>
 * Time: 14:37<br/>
 * To change this template use File | Settings | File Templates.
 * this is for testing for Spring AOP @Aspect annotation usage demo
 */
@Retention(value= RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Date2LongAnnotation {

}
