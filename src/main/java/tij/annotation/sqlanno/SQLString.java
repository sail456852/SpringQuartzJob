package tij.annotation.sqlanno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 12/24/18<br/>
 * Time: 10:42 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    int value() default 0;
    String name() default "";
    Constraints constrains() default @Constraints;
}
