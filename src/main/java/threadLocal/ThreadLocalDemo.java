package threadLocal;

import java.util.concurrent.CountDownLatch;

/**
 * https://www.cnblogs.com/jasongj/p/8079718.html
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {

        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for(int i = 1; i <= threads; i++) {

            //每个 thread 一个 StringBuilder 实例
            new Thread(() -> {
                for(int j = 0; j < 4; j++) {
                    StringBuilder str = Counter.counter.get();
                    Counter.counter.set(str.append(String.valueOf(j)));
                    System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                            Thread.currentThread().getName(),
                            Counter.counter.hashCode(),
                            Counter.counter.get().hashCode(),
                            Counter.counter.get().toString());
                }

                //设置新的实例，instance hashcode 改变
                Counter.counter.set(new StringBuilder("hello world"));
                System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                        Thread.currentThread().getName(),
                        Counter.counter.hashCode(),
                        Counter.counter.get().hashCode(),
                        Counter.counter.get().toString());
                countDownLatch.countDown();
            }, "thread - " + i).start();
        }
        countDownLatch.await();

    }

    private static class Counter {

        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };
    }

}
