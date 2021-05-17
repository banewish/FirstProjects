package Actor

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class HelloActor extends Actor {

import HelloActor._
  // У любого актора есть метод receive - который принимает частично примененную функцию
  // Частично примененную функция - это просто match {case ...}
  // Если case с условием есть то функция будет делать то что закодите, если нет не единого case подходящего под условие то ошибка

  // Выходит, что тут мы можем делать все что и в match, потому что это он и есть,
  // Например тут актор реагирует на одно сообщение и есть обработка для всех остальных сообщенией по умолчанию

  def receive = {

    case "hello" => println("hello Vasia")
    case "привет"       => println("здарова")
    case "Ты жопа"     => println("нет, ты")
    case str:String if str.length > 10 => println("че молчишь?")
    case 666 => println("a u lil devil?")
    case in: Int if in > 1 => println("just 555")
    case SpaceMarine(true) => println("For the Emperor!!!")
    case _ => println("Шо")
  }
}
object HelloActor {
  case class SpaceMarine(enough: Boolean = true){
    class gun(enough: Boolean = true)
  }
}



object Main extends App {

  val system = ActorSystem("HelloSystem") // хранилище для всех акторов

  // ВСЕ акторы должны быть созданы только через хранилище, по этому вы не сможете создать их через new
  // Для создания актора нужно заставить хранилище его создать и ввернуть ссылку (почтовый адрес для сообщений)
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")  // создание актора


  // передаем актору сообщение (хотя на самом деле передаем сообщение на его ссылку - почтовый адресс)
  // в будущем возможны ситуации, что ссылка у нас еще есть, а актор уже сдох - тогда будет ошибка и такое сообщение попадет в особому актору-мусорке

  helloActor ! "hello"  // hello Vasia
  helloActor ! "привет" // здарова
  helloActor ! "Ты жопа"
  helloActor ! "hdgsdhdhjdherhrgr"
  helloActor ! 666
  helloActor ! 2476
  helloActor ! "dfsfg"
  helloActor ! HelloActor.SpaceMarine(true)

}