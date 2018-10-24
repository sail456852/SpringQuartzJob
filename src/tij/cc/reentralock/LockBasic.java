package tij.cc.reentralock;

import org.junit.Test;

import static java.lang.Thread.yield;

public class LockBasic {

    private int localvar = 10;

    @Test
    public void mtTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                localvar++;
                System.out.println("LockBasic.t1  " + i + " --------  " + localvar);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 6000; j++) {
                localvar++;
                System.out.println("LockBasic.t2  " + j + " --------  " + localvar);
            }
            yield();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("localvar = " + localvar);
        System.out.println("LockBasic.main");
        System.out.println("LockBasic.main2");
    }

}
