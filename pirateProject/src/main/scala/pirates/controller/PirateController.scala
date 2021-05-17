package pirates.controller

import akka.http.scaladsl.marshalling.ToResponseMarshaller
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Route
import io.circe.{Encoder, Printer}
import pirates.model.Pirate
import pirates.service.PirateService
import utils.Controller

import scala.util.Success

class PirateController(pirateService: PirateService) extends Controller {

  import pirates.model.PirateImplicits._

  override def route: Route =
    getPirate ~
      createPirate ~
      updatePirate
      init

  protected def getPirate: Route = pathPrefix("pirate") {
    path("view" / IntNumber) { id => // тут мы указываем, что следующая часть пути состоит из /view и любого числа.
      // если мы указываем, что там межет быть число (IntNumber), то мы может считать его в переменную, для этого мы пишем id =>
      // То что будет в пути после /view/*** попадет в id
      get {
        onComplete(pirateService.getPirate(id)) { // метод onComplete это как будтобы матч на Future: Success и Failure
          case Success(v) => complete(v) // Чтобы метод умел возвращать ответ, нужно завернуть в запрос в complete
        }
      }
    }
  }
  protected def createPirate: Route = pathPrefix("pirate") {
    path("create") {
      (put & entity(as[Pirate]) ){ pirate =>
        // Scala не умеет автоматически запихивать данные в класс, для этого мы используем бибиотеку circe.
        // Для того. чтобы circe смог прочитать и запихнуть данные в класс нужно создать имплисит и внести его в контекст
        // (Сделать импорт из другого места или написать прям в классе)

        // Удалить импорт и сразу будет ошибка
        onComplete(pirateService.createPirate(pirate)) {
          case Success(_) => complete("created") // поскольку БД возвращает int, где 1 - true, 0 - false. И это безполезная инфа, то мы заменяем ответ
        }
      }
    }
  }
  protected def updatePirate: Route = pathPrefix("pirate") {
    path("update") {
      (post & entity(as[Pirate]) ){ pirate =>
        onComplete(pirateService.updatePirate(pirate)) {
          case Success(_) => complete("updated")
        }
      }
    }
  }
  protected def init: Route = pathPrefix("pirate") {
    path("init") {
      onComplete(pirateService.init) {
        case Success(_) => complete("inited")
      }
    }
  }


  // На самом деле, для того чтобы case class завернулся в json и вернулся в красивом ответе нужно какбы создать ответ на запрос HttpResponse
  // Тут мы формируем ответ с кодом 200 - успех, и указываем, что мы возвращаем json и собсн сам json от кейс класса.
  // Мы делаем это в имплисит def , т.е. неявно превращаем любой кейс класс с существующим Encoder в ответ
  implicit def jsonMarshaller[A](implicit encoder: Encoder[A], e: ToResponseMarshaller[HttpResponse]): ToResponseMarshaller[A] =

    e.compose(v =>
      HttpResponse(
        status = 200,
        entity = HttpEntity(MediaTypes.`application/json`, encoder.apply(v).printWith(Printer(true, " ")))
      ))
}