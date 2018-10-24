package tij.cc;

import java.util.concurrent.*;

public class CallableE5 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new CallableImpl(5));
        Future<Integer> future2 = executorService.submit(new CallableImpl(8));
        Future<Integer> future3 = executorService.submit(new CallableImpl(10));
        try {
            System.out.println("f1= " + future.get());
            System.out.println("f2= " + future2.get());
            System.out.println("f3= " + future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


class CallableImpl implements Callable<Integer> {
    private int fSize;

    public CallableImpl(int fSize) {
        this.fSize = fSize;
    }

    @Override
    public Integer call() throws Exception {
        return new Fibonacci(fSize).SumFib();
    }
}

class Fibonacci {

    private int fSize;
    private int[] arr ; // here may not be properly initialized
    private int sum = 0;

    public Fibonacci(int fSize) {
        arr = new int[fSize];
        this.fSize = fSize;
        for (int i = 0; i < arr.length; i++) {
           arr[i] = fib(i);
        }
    }

    public int fib(int n){
       if(n < 2) {
           return 1;
       }
       return fib(n -2 ) + fib( n - 1);
    }

    public int SumFib(){
        for (int i = 0; i < fSize; i++) {
            System.out.print(" " + arr[i] + "  ");
            sum += arr[i];

        }
        return sum;
    }

}
