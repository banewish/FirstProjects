package Actor

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.ask

import scala.concurrent.duration._
import akka.util.Timeout

import scala.concurrent.{Await, Future}
class Foods extends Actor {

  import FoodMain.FoodSeq

  def receive = {
    case foodSeq: FoodSeq =>
//      val sum = foodSeq.seqOfFood.map(_.calories).sum
//    sender ! sum
      val filterForCalories = foodSeq.seqOfFood.map(_.calories).filter(_ > 100)
      sender ! filterForCalories
  }
}

object FoodMain extends App {
  implicit val duration: Timeout = 2.seconds

  case class Food(name: String,calories: Int)
  case class FoodSeq(seqOfFood: Seq[Food])

//  val rise = Food("Рис",120)
//  val meat = Food("а пахне як",250)
//  val foodSeq = FoodSeq(Seq(rise,meat))
  val grecha = Food("греча",90)
  val meat = Food("а пахне як",200)
  val foodSeq = FoodSeq(Seq(grecha,meat))


      val system = ActorSystem("HelloSystem")
      val foodActor = system.actorOf(Props[Foods], name = "ActorFood")

      val foodFuture: Future[Any] = foodActor ? foodSeq
      println(Await.result(foodFuture, Duration.Inf))


//  println("foodSeq: " + foodSeq)
//  println("foodSeq.seqOfFood: " + foodSeq.seqOfFood)
  println("foodSeq.seqOfFood.map(_.calories): " + foodSeq.seqOfFood.map(_.calories))

}