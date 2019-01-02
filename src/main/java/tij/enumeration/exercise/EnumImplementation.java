package tij.enumeration.exercise;


import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/> E2
 * Date: 11/17/18<br/>
 * Time: 5:50 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
enum CartoonCharacter {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private static Random rand = new Random(47);
    public static CartoonCharacter next(){
        return values()[rand.nextInt(values().length)];
    }
}

public class EnumImplementation {

    public static void printNext(@NotNull CartoonCharacter rg){
        System.out.print(CartoonCharacter.next() + ", ");
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++) {
            printNext(cc);
        }
    }
}
