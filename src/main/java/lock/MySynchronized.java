package lock;

public class MySynchronized extends Thread
{
    private int val;

    public MySynchronized(int v)
    {
        val = v;
    }

    public void printVal(int v)
    {
        synchronized (MySynchronized.class)
        {
            while (true)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(v);
            }
        }
    }

    public void run()
    {
        printVal(val);
    }

    public static void main(String[] args) {
        MySynchronized f1 = new MySynchronized(1);
        MySynchronized f2 = new MySynchronized(2);
        f2.start();
        f1.start();
//        f2.start();
    }
}
