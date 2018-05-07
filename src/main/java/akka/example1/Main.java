package akka.example1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.example1.Greeter.*;


/**
 * 通过各个 Actor 的 props 函数构造对应的 Actor，并赋予名字
 */
public class Main {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("helloakka");
//    try {
        //#create-actors
        final ActorRef printer1 =
                system.actorOf(Printer.props("main-printer-1"), "main-printer");

        final ActorRef greeter1 =
                system.actorOf(Greeter.props("Greeter-1", printer1), "greeter-1");

        //#main-send-messages
        greeter1.tell(new WhoToGreet("Akka"), ActorRef.noSender());
        greeter1.tell(new Greet(), ActorRef.noSender());

        system.terminate();
    }
}
