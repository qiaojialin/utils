package thread;

public class JoinTest {
    public  static  void  main (String[]  args ) {
        SubThread thread  =  new  SubThread () ;

        thread.start () ;//子线程异步执行

        mainThreadOtherWork () ; //主线程异步工作

        try  {
            thread.join () ; // 在当前线程等待thread结束，可以设置超时时间
        }  catch  ( InterruptedException e) {
            e.printStackTrace () ;
        }
        System.out.println ("now all done.") ;
    }

    private  static  void  mainThreadOtherWork () {
        System.out.println ("main thread work start") ;
        try  {
            Thread.sleep ( 3000L ) ;
        }  catch  ( InterruptedException e) {
            e.printStackTrace () ;
        }
        System.out.println ("main thread work done.") ;
    }
}

class  SubThread  extends  Thread{
    @Override
    public  void  run () {
        working () ;
    }

    private  void  working () {
        System.out.println ("sub thread start working." ) ;
        busy () ;
        System.out.println ("sub thread stop working." ) ;
    }

    private  void  busy () {
        try  {
            sleep ( 5000L ) ;
        }  catch  ( InterruptedException e) {
            e.printStackTrace () ;
        }
    }

}