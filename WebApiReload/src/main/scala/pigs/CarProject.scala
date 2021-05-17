package pigs

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{pathPrefix, _}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import pigs.controller.MainExampleController

import scala.concurrent.Future

object CarProject extends App {
  val main = new CarProject
  main.start
}

class CarProject {
  def start: Future[Unit] = {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher


    val mainExampleController = new MainExampleController()


    val routes: Route =
      pathPrefix("api" / "v1") {
        mainExampleController.route
      }


    val bindingFuture = Http().bindAndHandle(routes, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    bindingFuture.map(_ => ())
  }
}
