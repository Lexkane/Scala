import akka.actor._

object ActorTest {


  case object Quit

  case class Message(s: String)

  case class Number(n: Int)


  class SimpleActor extends Actor {

    override def receive: Receive = {
      case Quit => println("Exiting")
      case Message(s) => println("Got a string " + s)
      case Number(i) => println("The number is" + i + ".")
    }

  }

  case class CountDown(n: Int)

  class CountingActor(name: String) extends Actor {
    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      super.preRestart(reason, message)
      //val counter=context.actorOf(Props(new CountingActor),"Actor")
    }

    override def receive: Receive = {

      case CountDown(n) => println(n)
        if (n > 0) {
          sender ! CountDown(n - 1)
        }
      case Quit => println("Exiting")

    }
  }


  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Main")


    val sa = system.actorOf(Props[ActorContext])

    /** sa.start()
      * sa ! Message("First message")
      * sa ! Number
      * sq ! Quit
      * *
      * val counter1 = new CountingActor("Thing1")
      *couner.start()
      * val counter2 = new CountingActor("Thing1")
      * counter2.start() */
  }

}
