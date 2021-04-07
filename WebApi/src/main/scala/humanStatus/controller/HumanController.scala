package humanStatus.controller

import akka.http.scaladsl.marshalling.ToResponseMarshaller
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import humanStatus.model.Human
import humanStatus.service.HumanService
import io.circe.{Encoder, Printer}
import utils.Controller

import scala.util.Success

class HumanController(humanService: HumanService) extends Controller {

  import humanStatus.model.HumanImplicits._

  override def route: Route =
    getHuman ~
      createHuman ~
      updateHuman

  protected def getHuman: Route = pathPrefix("human") {
    path("view" / IntNumber) { id =>
      get {
        onComplete(humanService.getHuman(id)) {
          case Success(v) => complete(v)
        }
      }
    }
  }

  protected def createHuman: Route = pathPrefix("human") {
    path("sozdat") {
      (put & entity(as[Human])) { human =>
        onComplete(humanService.createHuman(human)) {
          case Success(_) => complete("создался")
        }
      }
    }
  }

  protected def updateHuman: Route = pathPrefix("human") {
    path("izmen") {
      (post & entity(as[Human])) { human =>
        onComplete(humanService.updateHuman(human)) {
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




