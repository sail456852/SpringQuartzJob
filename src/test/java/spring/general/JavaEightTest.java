package spring.general;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/22/2019<br/>
 * Time: 3:43 PM<br/>
 * To change this template use File | Settings | File Templates.
 * General usage for testing Java 8 features
 * 1. stream
 * 2. lambda
 */
public class JavaEightTest {
    /**
     *  split a string into multiple sections,
     *  based on regex expresion
     */
    @Test
    public void streamRegExStringSplit() {
        String str = "XML,CSS,HTML";
        Pattern.compile(",")
                .splitAsStream(str)
                .forEach(System.out::println);
    }
    
}
