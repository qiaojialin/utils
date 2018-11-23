package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample1 {

    private Lock lock = new ReentrantLock();

    private void criticalZone(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockExample1 lockTest = new LockExample1();

        for(int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    lockTest.criticalZone();
                }
            }, "thread-" + i).start();
        }
    }
}
