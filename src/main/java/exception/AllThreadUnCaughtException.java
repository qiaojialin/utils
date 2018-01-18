package exception;

public class AllThreadUnCaughtException {

    public static void main(String[] args) {
        //为当前线程设置默认的未捕获异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new MyCrashHandler());
        //执行子线程，产生异常
        new TestThread().start();
    }

    static class TestThread extends Thread {

        @Override
        public void run() {
            super.run();
            //在子线程中模拟一个异常
            System.out.println("1/0 = " + 1 / 0);
        }

    }

    static class MyCrashHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread arg0, Throwable arg1) {
            //当程序发生未捕获的异常时，会执行该方法
            System.out.println("抱歉，程序出异常了..." + arg0.toString() +" "+arg1.getMessage());
            arg1.printStackTrace();
        }

    }
}

