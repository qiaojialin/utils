package thread;

public class TestThread {
    public static void main(String[] args) {
        System.out.println("main thread starts");
        FactorialThread thread = new FactorialThread(10);
        thread.start();    //将自动进入run()方法
        System.out.println("main thread ends ");
    }
}


class FactorialThread extends Thread {
    private int num;

    public FactorialThread(int num) {
        this.num = num;
    }

    public void run() {
        System.out.println("new thread starts");
        System.out.println(Thread.currentThread().getName() + ": "+ num);
        System.out.println("new thread ends");
    }
}
