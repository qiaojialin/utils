package atomic;

import java.util.ArrayList;
import java.util.List;

/**
 * 不保证原子性
 * https://www.cnblogs.com/dolphin0520/p/3920373.html
 */
public class Testvolatile {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Testvolatile test = new Testvolatile();
        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            };
            thread.start();
            threads.add(thread);
        }

        for(Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(test.inc);
    }
}