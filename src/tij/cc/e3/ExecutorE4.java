package tij.cc.e3;

import tij.cc.e1.Fibonacci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ExecutorE4 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 2 ; i++) {
            executorService.submit(new Finobacci());
        }
        executorService.shutdown();
    }

}


class Finobacci implements Supplier<Integer>, Runnable{

    private int count = 0;
    @Override
    public Integer get() { return fib(count++); }

    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }


    @Override
    public void run() {
        Stream.generate(new Fibonacci())
                .limit(5)
                .map(n -> n + " ")
                .forEach(System.out::print);
    }

}
