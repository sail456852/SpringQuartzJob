package tij.cc.e1;// generics/Fibonacci.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Generate a Fibonacci sequence

import java.util.function.Supplier;
import java.util.stream.Stream;

public class FibonacciMT implements Runnable, Supplier<Integer> {
    private int count = 0;
    @Override
    public Integer get() { return fib(count++); }

    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }


    @Override
    public void run() {
        Stream.generate(new FibonacciMT())
                .limit(18)
                .map(n -> n + " ")
                .forEach(System.out::print);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            FibonacciMT thread = new FibonacciMT();
            Thread thread1 = new Thread(thread);
            thread1.start();
        }
    }
}
/* Output:
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597
2584
*/