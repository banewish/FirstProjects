package pigs.controller

import akka.http.scaladsl.marshalling.ToResponseMarshaller
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Route
import io.circe.{Encoder, Printer}
import pigs.model.Pig
import pigs.service.PigService
import utils.Controller

import scala.util.Success

class PigController(pigService: PigService) extends Controller {

  import pigs.model.PigImplicits._

  override def route: Route =
    getPig ~
    createPig ~
    updatePig

  protected def getPig: Route = pathPrefix("pig") { // тут pathPrefix указывает начало пути ссылки,в данном случае /pig
    path("view" / IntNumber) { id => // тут мы указываем, что следующая часть пути состоит из /view и любого числа.
      // если мы указываем, что там межет быть число (IntNumber), то мы может считать его в переменную, для этого мы пишем id =>
      // То что будет в пути после /view/*** попадет в id
      get { // выходит, что путь у метода /pig/view/{число}
        onComplete(pigService.getPig(id)) { // метод onComplete это как будтобы матч на Future: Success и Failure
          case Success(v) => complete(v) // Чтобы метод умел возвращать ответ, нужно завернуть в запрос в complete
        }
      }
    }
  }
  protected def createPig: Route = pathPrefix("pig") {
    path("sozdat") {
      (put & entity(as[Pig]) ){ pig => // тут мы пытаемся считать из тела запроса данные и запихнуть их в класс при помощи entity(as[Pig])
        // Scala не умеет автоматически запихивать данные в класс, для этого мы используем бибиотеку circe.
        // Для того. чтобы circe смог прочитать и запихнуть данные в класс нужно создать имплисит и внести его в контекст
        // (Сделать импорт из другого места или написать прям в классе)
        // Мы импортим такой на 16 строке import pigs.model.PigImplicits._
        // Удалить импорт и сразу будет ошибка
        onComplete(pigService.createPig(pig)) {
          case Success(_) => complete("создался") // поскольку БД возвращает int, где 1 - true, 0 - false. И это безполезная инфа, то мы заменяем ответ
        }
      }
    }
  }
  protected def updatePig: Route = pathPrefix("pig") {
    path("izmen") {
      (post & entity(as[Pig]) ){ pig =>
        onComplete(pigService.updatePig(pig)) {
          case Success(_) => complete("изменился")
        }
      }
    }
  }


  // На самом деле, для того чтобы case class завернулся в json и вернулся в красивом ответе нужно какбы создать ответ на запрос HttpResponse
  // Тут мы формируем ответ с кодом 200 - успех, и указываем, что мы возвращаем json и собсн сам json от кейс класса.
  // Мы делаем это в имплисит def , т.е. неявно превращаем любой кейс класс с существующим Encoder в ответ
  implicit def jsonMarshaller[A](implicit encoder: Encoder[A], e: ToResponseMarshaller[HttpResponse]): ToResponseMarshaller[A] =
  // Буква A это абстрактный тип, значит любой тип. Например, вместе A тут может быть Pig и мы создадим ответ со свиньей.
  // Такие же буквы есть у Option, Try, Either, Future - все это классы, но работают они то любыми классами
    e.compose(v =>
      HttpResponse(
        status = 200,
        entity = HttpEntity(MediaTypes.`application/json`, encoder.apply(v).printWith(Printer(true, " ")))
      ))
}
