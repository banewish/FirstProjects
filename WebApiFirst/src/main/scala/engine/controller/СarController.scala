package engine.controller
import akka.http.scaladsl.marshalling.ToResponseMarshaller
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import engine.model.{Car, Engine}
import engine.service.CarService
import io.circe.{Encoder, Printer}
import utils.Controller

import scala.util.Success

class CarController(carService: CarService) extends Controller {
  import engine.model.CarImplicits._

  override def route: Route =
    getCar ~
      createCar ~
      updateCar ~
      getEngine ~
      updateEngine

  protected def getCar: Route = pathPrefix("car") {
    path("view" / IntNumber) { id =>
      get {
        onComplete(carService.getCar(id)) {
          case Success(v) => complete(v)
        }
      }
    }
  }

  protected def getEngine: Route = pathPrefix("car") {
    path("view" / "engine" / IntNumber) { id =>
      get {
        onComplete(carService.getEngine(id)) {
          case Success(v) => complete(v)
        }
      }
    }
  }

  protected def createCar: Route = pathPrefix("car") {
    path("sozdat") {
      (put & entity(as[Car])) { car =>
        onComplete(carService.createCar(car)) {
          case Success(_) => complete("создался")
        }
      }
    }
  }

  protected def updateCar: Route = pathPrefix("car") {
    path("izmen") {
      (post & entity(as[Car])) { car =>
        onComplete(carService.updateCar(car)) {
          case Success(_) => complete("изменился")
        }
      }
    }
  }

  protected def updateEngine: Route = pathPrefix("car") {
    path("izmen" / "engine" / IntNumber) { id =>
      (post & entity(as[Engine])) { car =>
        onComplete(carService.updateEngine(id, car)) {
          case Success(_) => complete("изменился")
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