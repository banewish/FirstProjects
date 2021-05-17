package pigs.controller

import akka.http.scaladsl.marshalling.ToResponseMarshaller
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Route
import io.circe.{Encoder, Printer}
import pigs.model.Car
import utils.Controller
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._


class MainExampleController extends Controller {

  override def route: Route =
    pathPrefix("example") {
      rawHelloWorld ~ // url localhost:8080/api/v1/example/raw/helloWorld
      jsonHelloWorld ~ // url localhost:8080/api/v1/example/json/helloWorld
      jsonbodyCar ~ // url localhost:8080/api/v1/example/jsonbody/car
      read // url localhost:8080/api/v1/example/read/all не будет работать в браузере
    }

  // Возвращаем просто строчку, без джисона
  protected def rawHelloWorld: Route =
    path( "raw" / "helloWorld") {
      get {
        complete("hello world")
      }
    }

  // возвращаем джисон
  protected def jsonHelloWorld: Route =
    path( "json" / "helloWorld") {
      get {
        import io.circe.generic.auto._ // лень создавать декодер и енкодер, пусть создаст автоматом

        case class StrInJson(str: String)
        complete(StrInJson("hello world"))
      }
    }

  // возвращаем джисон
  protected def jsonbodyCar: Route =
    path("jsonbody" / "car") {
      get {

        val carHardCode = Car(Some(15), 1500.25, "BMV")
        complete(carHardCode)
      }
    }


  protected def read: Route =
    parameters("name".?, "lastName".?) { (nameOpt, lastNameOpt) => // читаем параметры. Перечисляем параметры через запятую, значение из параметра ляжет в сотв. переменную перед стрелочкой
      optionalHeaderValueByName("Age") { ageOpt => // хедеры читаем по одному. Для прочтения двух нужно втыкнуть optionalHeaderValueByName внутри другого optionalHeaderValueByName. Хедеры называют с большой буквы
        path("read" / "all" ) {
          (post & entity(as[Option[Car]]))  { carOpt =>
            // тут доступны все веременные nameOpt, lastNameOpt, ageOpt, carOpt
            complete(s"Я прочитал из параметра name: $nameOpt, из параметра lastName:$lastNameOpt, из хедера Age:$ageOpt. Моя машина: $carOpt")
          }
        }
      }
    }

  implicit def jsonMarshaller[A](implicit encoder: Encoder[A], e: ToResponseMarshaller[HttpResponse]): ToResponseMarshaller[A] =
    e.compose(v =>
      HttpResponse(
        status = 200,
        entity = HttpEntity(MediaTypes.`application/json`, encoder.apply(v).printWith(Printer(true, " ")))
      ))
}
