package exception;

import java.util.concurrent.*;

public class SubmitCaught
{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        
        Future<?> future = exec.submit(new Task());
        exec.shutdown();
        try {
            future.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            System.out.println("==Exception: "+e.getMessage());
        }
    }
}


class Task implements Runnable
{
    @Override
    public void run()
    {
        int[] a = new int[10000000];

//        System.out.println(3/2);
//        System.out.println(3/0);
//        System.out.println(3/1);
    }
}