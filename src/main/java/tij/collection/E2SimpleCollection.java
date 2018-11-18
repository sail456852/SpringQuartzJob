package tij.collection;

import java.util.HashSet;

public class E2SimpleCollection {
    public static void main(String[] args) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            hs.add(i);
        }
        for (Integer in:
             hs) {
            System.out.println("in = " + in);
        }
    }
}
