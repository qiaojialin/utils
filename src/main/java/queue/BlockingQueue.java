package queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



public class BlockingQueue {
    //阻塞队列，FIFO
    private static LinkedBlockingQueue<Integer> concurrentLinkedQueue = new LinkedBlockingQueue<Integer>();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new Producer("producer1"));
        executorService.submit(new Producer("producer2"));
        executorService.submit(new Producer("producer3"));
        executorService.submit(new Consumer("consumer1"));
        executorService.submit(new Consumer("consumer2"));
        executorService.submit(new Consumer("consumer3"));
        executorService.shutdown();

    }

    static class Producer implements Runnable {
        private String name;

        public Producer(String name) {
            this.name = name;
        }

        public void run() {
            for (int i = 1; i < 5; ++i) {
                System.out.println(name+ "  生产： " + i);
                //concurrentLinkedQueue.add(i);
                try {
                    concurrentLinkedQueue.put(i);
                    Thread.sleep(1000); //模拟慢速的生产，产生阻塞的效果
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        }
    }

    static class Consumer implements Runnable {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }
        public void run() {
            for (int i = 1; i < 5; ++i) {
                try {
                    //必须要使用take()方法在获取的时候阻塞
                    System.out.println(name+"消费： " +  concurrentLinkedQueue.take());
                    //使用poll()方法 将产生非阻塞效果
                    //System.out.println(name+"消费： " +  concurrentLinkedQueue.poll());

                    //还有一个超时的用法，队列空时，指定阻塞时间后返回，不会一直阻塞
                    //但有一个疑问，既然可以不阻塞，为啥还叫阻塞队列？
                    //System.out.println(name+" Consumer " +  concurrentLinkedQueue.poll(300, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }
}
