package tij.enumeration.example;




import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 11/17/18<br/>
 * Time: 5:50 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private Random rand = new Random(47);
    public CartoonCharacter next(){
        return values()[rand.nextInt(values().length)];
    }
}
public class EnumImplementation {

    public static <T> void printNext(@NotNull Generator<T> rg){
        System.out.print(rg.next() + ", ");
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++) {
            printNext(cc);
        }
    }
}
