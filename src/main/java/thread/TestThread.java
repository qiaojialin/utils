package thread;

import org.apache.iotdb.session.IoTDBSessionException;
import org.apache.iotdb.session.Session;

public class TestThread {
    public static void main(String[] args) {
        System.out.println("main thread starts");
        for (int i = 0; i < 100; i++) {
            FactorialThread thread = new FactorialThread(i);
            thread.start();
        }
        System.out.println("main thread ends ");
    }
}


class FactorialThread extends Thread {
    private int num;

    public FactorialThread(int num) {
        this.num = num;
    }

    public void run() {
        Session session = new Session("127.0.0.1", 6667, "root", "root");
        try {
            session.open();
        } catch (IoTDBSessionException e) {
            e.printStackTrace();
        }
        try {
            session.close();
        } catch (IoTDBSessionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": "+ num);

    }
}
