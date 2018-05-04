package thread;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest
{
    public static void main(String[] args)
    {
        int taskSize = 5;

        ExecutorService executor = Executors.newFixedThreadPool(taskSize);
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 1; i <= taskSize; i++)
        {
            int sleep = taskSize - i; // 睡眠时间

            int value = i; // 返回结果

            // 向线程池提交任务
            completionService.submit(new ReturnAfterSleepCallable(sleep, value));
        }

        // 按照完成顺序,打印结果
        for (int i = 0; i < taskSize; i++)
        {
            try
            {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        // 所有任务已经完成,关闭线程池
        System.out.println("all over.");
        executor.shutdown();
    }
}