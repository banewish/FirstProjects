package Actor

import ZabegSvinkiMain.Zabeg
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.ask

import scala.concurrent.duration._
import akka.util.Timeout

import scala.concurrent.{Await, Future}

class ZabegSvinki extends Actor {

  def receive = {
    case zabeg:Zabeg =>
      if(zabeg.svinki.speed > zabeg.svinki2.speed){
        sender ! zabeg.svinki.name
      }else{
        sender ! zabeg.svinki2.name
      }
    case _ => println("Шо?")

  }
}


object ZabegSvinkiMain extends App {
  implicit val duration: Timeout = 2.seconds
  case class Svinki(name:String,speed:Int)
  case class Zabeg(svinki:Svinki,svinki2: Svinki)

  val svinka1 = Svinki("Евкакий",32)
  val svinka2 = Svinki("Алёшка",25)

  val svinka3 = Svinki("Евкакий",24)
  val svinka4= Svinki("hhhhh",26)

  val svinka5 = Svinki("Евкайкий",30)
  val svinka6 = Svinki("Алекkkkkkkсей",15)




  val zabeg = Zabeg(svinka2,svinka1)
  val zabeg2 = Zabeg(svinka3,svinka4)
  val zabeg3 = Zabeg(svinka4,svinka6)

  val system = ActorSystem("HelloSystem")
  val pokupkaSvinkiActor = system.actorOf(Props[ZabegSvinki], name = "sravnit")

  val pokupkaSvinkiFuture: Future[Any] = pokupkaSvinkiActor ? zabeg
  println(Await.result(pokupkaSvinkiFuture, Duration.Inf))
  //строка для варианта с методом (снизу)
  println(zabegVMethode(zabeg))


  val pokupkaSvinkiFuture2: Future[Any] = pokupkaSvinkiActor ? zabeg2
  println(Await.result(pokupkaSvinkiFuture2, Duration.Inf))
  //строка для варианта с методом (снизу)
  println(zabegVMethode(zabeg2))

  val pokupkaSvinkiFuture3: Future[Any] = pokupkaSvinkiActor ? zabeg3
  println(Await.result(pokupkaSvinkiFuture3, Duration.Inf))
  //строка для варианта с методом (снизу)
  println(zabegVMethode(zabeg3))


  // такой же самый вариант как можно было написать через метод
  def zabegVMethode(zabeg: Zabeg): String = {
    if(zabeg.svinki.speed > zabeg.svinki2.speed){
      zabeg.svinki.name
    }else{
      zabeg.svinki2.name
    }
  }

}