package synchronize;

import java.util.ArrayList;

public class InsertDataSync {

    public static void main(String[] args)  {
        final Insert insertData = new Insert();

        new Thread(() -> insertData.insert(Thread.currentThread())).start();


        new Thread() {
            public void run() {
                insertData.insert(Thread.currentThread());
            };
        }.start();
    }
}

class Insert {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public synchronized void insert(Thread thread){
        for(int i=0;i<5;i++){
            System.out.println(thread.getName()+"在插入数据"+i);
            arrayList.add(i);
        }
    }
}
