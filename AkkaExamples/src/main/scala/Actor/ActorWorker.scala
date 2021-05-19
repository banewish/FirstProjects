package Actor

import WorkMain.{Worker}
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.ask

import scala.concurrent.duration._
import akka.util.Timeout

import scala.concurrent.{Await, Future}

class WorkHuman extends Actor {

  def receive = {
    case worker:Worker =>
      if(worker.age > 0){
        sender ! worker.age
      }else{
        val worker2 = worker.copy(age = worker.age* -2)
        sender ! worker2.age
      }
    case _ => println("да-да?")
  }
}


object WorkMain extends App {
  implicit val duration: Timeout = 2.seconds
  case class Worker(id:Option[Int],age:Int,name:String)


  val worker =Worker(Some(10),-1,"Чебурашка")


  val system = ActorSystem("HelloSystem")
  val workActor = system.actorOf(Props[WorkHuman], name = "work")

  val workFuture: Future[Any] = workActor ? worker
  println(Await.result(workFuture, Duration.Inf))
}