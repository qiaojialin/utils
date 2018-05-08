package akka.supervisor;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

/**
 * Created by liubenlong on 2017/1/12.
 */
public class RestartActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props() {
        return Props.create(RestartActor.class, () -> new RestartActor());
    }

    public  enum  Msg{
        DONE, RESTART, RESUME
    }


    @Override
    public void preStart() throws Exception {
        System.out.println("preStart    hashCode=" + this.hashCode());
    }
    @Override
    public void postStop() throws Exception {
        System.out.println("postStop    hashCode=" + this.hashCode());
    }



    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("preRestart    hashCode=" + this.hashCode());
    }
    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart    hashCode=" + this.hashCode());
    }


    @Override
    public void onReceive(Object o) throws Throwable {
        if(o == Msg.DONE){
            log.info("I am down");
            getContext().stop(getSelf());
            log.info("dou dei si");
            getContext().system().terminate();
        }else if(o == Msg.RESTART){
            log.info("I will be restarted");
            throw new NullPointerException();
        } else if (o == Msg.RESUME) {
            log.info("I will be resume");
            throw new ArithmeticException();
        }else{
            unhandled(o);
        }

    }
}
