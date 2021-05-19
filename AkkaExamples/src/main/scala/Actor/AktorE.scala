package Actor
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import AskTest.sum
import AskTest.Sum

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.matching.Regex

case object AskNameMessage

class TestActor extends Actor {

  def receive = {

    case AskNameMessage => // отвечаем на запрос "спросить"
      sender ! "Hello"
//      sender ! println(sum.a + sum.b)

        sender ! println(sum.a + sum.b)
    case _ => "asasa"
//      val number: Regex = "[0-9]".r
  }
}
object AskTest extends App {



  val system = ActorSystem("AskTestSystem")
  val myActor = system.actorOf(Props[TestActor], name = "myActor")

  implicit val timeout = Timeout(3 seconds)

case class Sum(a: Int, b: Int)
  var sum = Sum(5,5)

  // (2) немного другой способ запросить информацию у другого субъекта
  val future2: Future[String] = ask(myActor, AskNameMessage).mapTo[String]
  val result2 = Await.result(future2, 1 second)
  println(result2)



  // знак ! возвращает юнит
  // а ? какой то ответ
}