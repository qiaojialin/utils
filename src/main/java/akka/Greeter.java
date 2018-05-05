package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.Printer.Greeting;

/**
 * Greeter初始化包括一个自己独有的message和对应的printer actor
 *
 * 接收到 WhoToGreat 后，就执行message拼接动作
 * 接收到 Greet 后，就向printer发送 Greeting
 */
public class Greeter extends AbstractActor {

    static public Props props(String message, ActorRef printerActor) {
        return Props.create(Greeter.class, () -> new Greeter(message, printerActor));
    }

    //#greeter-messages
    static public class WhoToGreet {
        public final String who;

        public WhoToGreet(String who) {
            this.who = who;
        }
    }

    //#greeter-messages
    static public class Greet {
        public Greet() {
        }
    }


    private final String message;
    private final ActorRef printerActor;
    private String greeting = "";

    public Greeter(String message, ActorRef printerActor) {
        this.message = message;
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WhoToGreet.class, wtg -> {
                    this.greeting = message + ", " + wtg.who;
                })
                .match(Greet.class, x -> {
                    //#greeter-send-message
                    printerActor.tell(new Greeting(greeting), getSelf());
                })
                .build();
    }
}
