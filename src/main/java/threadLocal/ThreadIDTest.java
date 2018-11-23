package threadLocal;

public class ThreadIDTest {
    public static void main(String[] args) {

        for(int i = 0; i < 10;i++ ) {
            new Thread(() -> System.out.println(ThreadID.get())).start();
        }

    }
}


class ThreadID {
    private static volatile int nextID = 0;
    private static class ThreadLocalID extends ThreadLocal<Integer> {
        protected synchronized Integer initialValue() {
            return nextID ++;
        }
    }
    private static ThreadLocalID threadLocalID = new ThreadLocalID();
    public static int get() {
        return threadLocalID.get();
    }

    public static void set(int i) {
        threadLocalID.set(i);
    }
}