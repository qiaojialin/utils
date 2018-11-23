package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {

    private Lock lock = new ReentrantLock();

    private void criticalZone() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "获得了锁");

                // 占着锁不动
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "没抢着锁");
        }
    }

    public static void main(String[] args) {
        TryLockTest lockTest = new TryLockTest();

        new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.criticalZone();
            }
        }, "thread-1").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.criticalZone();
            }
        }, "thread-2").start();
    }
}
