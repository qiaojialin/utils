package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PetersonLock implements Lock {

    private boolean[] flag = new boolean[2];
    private int victim;

    @Override
    public void lock() {
        int i = Integer.valueOf(Thread.currentThread().getName());
        int j = 1 - i;
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) ;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        int i = Integer.valueOf(Thread.currentThread().getName());
        flag[i] = false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

public class Peterson implements Runnable {

//    PetersonLock lock = new PetersonLock();
    Lock lock = new ReentrantLock();
    private int val = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
//            synchronized (this) {
                val++;
//            }
//            lock.lock();
//            try {
//                val ++;
//            } finally {
//                lock.unlock();
//            }
        }
    }

    public int getVal() {
        return val;
    }

    public static void main(String[] args) {
        Peterson peterson = new Peterson();
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(peterson);
            thread.setName(i + "");
            thread.start();
        }
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(peterson.getVal());
    }
}
