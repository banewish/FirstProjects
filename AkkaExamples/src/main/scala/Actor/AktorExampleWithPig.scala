package Actor
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.matching.Regex
import AskTestPig.pigAndMoney
import AskTestPig.PigAndMoney


case object AktorExampleWithPig

class TestPig extends Actor {

  def receive = {
    case AktorExampleWithPig =>
      sender ! "shit"
      sender ! println(pigAndMoney.priceForKg * pigAndMoney.amountOfMeatInKg)
    case _ => "bebeb"
    //
  }
}
object AskTestPig extends App {

  val system = ActorSystem("AskTestSystem")
  val myActor = system.actorOf(Props[TestPig], name = "myActor")

  implicit val timeout = Timeout(2 seconds)

  case class PigAndMoney(priceForKg: Int, amountOfMeatInKg : Int)
  val pigAndMoney = PigAndMoney(120,3)
  val future2: Future[String] = ask(myActor, AktorExampleWithPig).mapTo[String]
  val result2 = Await.result(future2, 1 second)
  println(result2)





  // знак ! возвращает юнит
  // а ? какой то ответ
}