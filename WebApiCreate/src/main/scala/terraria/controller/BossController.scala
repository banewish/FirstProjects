package terraria.controller

import akka.http.scaladsl.marshalling.ToResponseMarshaller
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Route
import io.circe.{Encoder, Printer}
import terraria.model.Boss
import terraria.service.BossService
import utils.Controller
import scala.util.Success

class BossController(bossService: BossService) extends Controller {

  import terraria.model.BossImplicit._

  override def route: Route =
    getBoss ~
      createBoss ~
      updateBoss

  protected def getBoss: Route = pathPrefix("boss") {

    path("view" / IntNumber) {
      id =>
        get {
          onComplete(bossService.getBoss(id)) {
            case Success(v) => complete(v)
          }
        }
    }
  }

  protected def createBoss: Route = pathPrefix("boss") {
    path("create") {
      (put & entity(as[Boss])) {
        boss =>
          onComplete(bossService.createBoss(boss)) {

            case Success(_) => complete("created")
          }
      }
    }
  }
  protected def updateBoss: Route = pathPrefix("boss") {
    path("update") {
      (post & entity(as[Boss])) { boss =>
        onComplete(bossService.updateBoss(boss)) {
          case Success(_) => complete("updated")
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

