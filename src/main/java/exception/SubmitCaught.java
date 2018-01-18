package exception;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitCaught
{
    public static void main(String[] args)
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> future = exec.submit(new Task());
        exec.shutdown();
        try
        {
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
        System.out.println(3/2);
        System.out.println(3/0);
        System.out.println(3/1);
    }
}