package tij.cc.e1;

import static java.lang.Thread.yield;

public class MyThreadEx1 implements Runnable {
    public MyThreadEx1() {
        System.out.println("MyThreadEx1.MyThreadEx1 starts");
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            System.out.println("I am in MyThreadEx1.run  " + i);
            yield();
        }
    }

    public static void main(String[] args) {
//        MyThreadEx1 myThreadEx1 = new MyThreadEx1();
//        myThreadEx1.run();
        for (int i = 0; i < 3 ; i++) {
            Thread myThreadEx1 = new Thread(new MyThreadEx1());
            myThreadEx1.start();
        }
        System.out.println("MyThreadEx1.main  ends!");
    }
}
