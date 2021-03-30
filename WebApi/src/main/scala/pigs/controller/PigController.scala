package pigs.controller

import akka.http.scaladsl.marshalling.ToResponseMarshaller
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Route
import io.circe.generic.auto._
import io.circe.{Encoder, Printer}
import pigs.service.PigService
import utils.Controller

import scala.util.Success

class PigController(pigService: PigService) extends Controller {

  override def route: Route = getPig

  protected def getPig: Route = pathPrefix("pig") {
    path("view" / IntNumber) { id =>
      get {
        onComplete(pigService.getPig(id)) {
          case Success(v) => complete(v)
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
