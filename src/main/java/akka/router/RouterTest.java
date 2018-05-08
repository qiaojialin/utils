package akka.router;

import akka.actor.*;
import akka.inbox.InboxTest;
import akka.routing.*;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 一个Router Actor管理5个worker actor，接收到消息就路由至worker，worker挂掉就从队列中清除
 */
public class RouterTest extends UntypedAbstractActor {

    public Router router;

    {
        ArrayList<Routee> routees = new ArrayList<>();
        for(int i = 0; i < 3; i ++) {
            //借用上面的 inboxActor
            ActorRef worker = getContext().actorOf(Props.create(InboxTest.class), "worker_" + i);
            getContext().watch(worker);//监听
            routees.add(new ActorRefRoutee(worker));
        }
        /**
         * RoundRobinRoutingLogic: 轮询
         * BroadcastRoutingLogic: 广播
         * RandomRoutingLogic: 随机
         * SmallestMailboxRoutingLogic: 空闲
         */
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if(o instanceof InboxTest.Msg){
            router.route(o, getSender());//进行路由转发
        }else if(o instanceof Terminated){
            router = router.removeRoutee(((Terminated)o).actor());//发生中断，将该actor删除。当然这里可以参考之前的actor重启策略，进行优化，为了简单，这里仅进行删除处理
            System.out.println(((Terminated)o).actor().path() + " 该actor已经删除。router.size=" + router.routees().size());

            if(router.routees().size() == 0){//没有可用actor了
                System.out.print("没有可用actor了，系统关闭。");
                flag.compareAndSet(true, false);
                getContext().system().terminate();
            }
        }else {
            unhandled(o);
        }

    }


    public  static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef routerTest = system.actorOf(Props.create(RouterTest.class), "RouterTest");

        int i = 1;
        while(flag.get()){
            System.out.println(i);
            routerTest.tell(InboxTest.Msg.WORKING, ActorRef.noSender());

            if(i % 3 == 0) routerTest.tell(InboxTest.Msg.CLOSE, ActorRef.noSender());

            Thread.sleep(500);

            i ++;
        }
    }
}