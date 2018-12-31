package tij.annotation.sqlanno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 12/27/18<br/>
 * Time: 9:58 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLDouble {
     String name() default "";
     double value() default 0;
     Constraints constrains() default @Constraints;
}
