package spring.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Administrator<br/>
 * Date: 2018/11/21<br/>
 * Time: 14:38<br/>
 * To change this template use File | Settings | File Templates.
 */
@Component
@Aspect
public class DateHandlerAOP {

    @Pointcut(value = "@annotation(spring.springaop.Date2LongAnnotation)")
    public void processPointCut(){
//        System.err.println("DateHandlerAOP.processPointCut"); // did't run
        // IDEA prompts : Warning:(20, 6) Pointcut methods should have empty body before committing
    }

    @Around("processPointCut()")
    public Object pointCutAdvice(ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.err.println("arg = " + arg);
        }

        try {
            System.err.println("DateHandlerAOP.pointCutAdvice"); // did run but only once
            return joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.err.println("DateHandlerAOP.pointCutAdvice error return null!");
            return null;
        }
    }
}
