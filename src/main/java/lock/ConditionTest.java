package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {
        LockedQueue queue = new LockedQueue(10000);
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 100; i ++) {
            Thread a = new Thread(new Producer(queue));
            threads.add(a);
            a.start();
            a = new Thread(new Consumer(queue));
            threads.add(a);
            a.start();
        }
        for(Thread a: threads) {
            a.join();
        }
    }
}

class Producer implements Runnable {

    private LockedQueue queue;

    public Producer(LockedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100000; i++) {
            queue.enq(1);
        }
    }



}

class Consumer implements Runnable {

    private LockedQueue queue;

    public Consumer(LockedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            int x = queue.deq();
            assert x == 1;
            if(i == 999)
                System.out.println(Thread.currentThread().getName() + " ok");
        }
    }
}

class LockedQueue {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final int[] items;
    int tail, head, count;
    public LockedQueue(int capacity) {
        items = new int[capacity];
    }

    public void enq(int x) {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[tail++] = x;
            if(tail == items.length)
                tail = 0;
            ++ count;
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int deq() {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            int x = items[head++];
            if(head == items.length)
                head = 0;
            -- count;
            notFull.signalAll();
            return x;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return -1;
    }

}

