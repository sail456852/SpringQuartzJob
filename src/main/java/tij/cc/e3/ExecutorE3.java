package tij.cc.e3;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorE3 {
    public static void main(String[] args) {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5 ; i++) {
            newCachedThreadPool.submit(new RunnableImpl());
        }
        newCachedThreadPool.shutdown();
    }
}

/**
 * we call this task
 */
class RunnableImpl implements Runnable{
    private static int taskCount = 0;
    private final int id = taskCount++;

    public RunnableImpl() {
        System.out.println("RunnableImpl.RunnableImpl ctor starts! " + id);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("RunnableImpl.run");
            Thread.yield();
        }
        System.out.println("RunnableImpl.run task terminates " + id);
    }
}
