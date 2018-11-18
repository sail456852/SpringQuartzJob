package tij.enumeration.example;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 11/17/18<br/>
 * Time: 11:01 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Enums {

    private static Random rand = new Random(47);
    public static <T extends Enum<T>> T random(Class<T> ec) {
//        if(ec instanceof  Enums){
//            Enum ecc = (Enum<T>) ec;
//            for (T t: ec.getEnumConstants()) {
//                return ec.values[rand.nextInt(ec.values().length)];
//            }
//        }
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
