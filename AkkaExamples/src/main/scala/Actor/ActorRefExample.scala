package Actor

import ZodaniyaActor.{ZadanieForWorker, Zodanie}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class WorkerActor extends Actor {
  def receive = {  //4
    case zodanie: Zodanie =>  //5
println(zodanie)
  }
}


class BossActor extends Actor {

  def receive = {
    case zadanieForWorker:ZadanieForWorker => //2
      zadanieForWorker.worker ! Zodanie(zadanieForWorker.taskDescription)  ;println("bossActor") //3
    case _ => println("Шо?")
  }
}

object ZodaniyaActor {
  case class Zodanie(description: String)
  case class ZadanieForWorker(worker: ActorRef, taskDescription: String)


}
object MainBossWorker extends App {

  val system = ActorSystem("HelloSystem")
  val workerActor = system.actorOf(Props[WorkerActor], name = "workerActor")
  val zadanieForWorker = ZadanieForWorker(workerActor, "KOL")
  val bossActor = system.actorOf(Props[BossActor], name = "bossActor")
  bossActor ! zadanieForWorker //1

}