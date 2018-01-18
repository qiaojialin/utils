package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadStop {
    private static ExecutorService executor;

    public static void main(String[] args) {
        executor = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++) {
            executor.submit(new MyThread());
        }
        executor.shutdown();
    }
}


class MyThread implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " produce: "+i);
        }
    }
}