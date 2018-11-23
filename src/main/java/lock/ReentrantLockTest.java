package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private Lock lock = new ReentrantLock();

    private void criticalZone() {

        for (int i = 0; i < 5; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "第" + i + "次获得了锁");
            } finally {
                System.out.println(Thread.currentThread().getName() + "第" + i + "次释放了锁");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();

        new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.criticalZone();
            }
        }, "thread-only").start();
    }

}
