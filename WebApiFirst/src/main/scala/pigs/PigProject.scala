package pigs

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{pathPrefix, _}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import dao.PigDAOImpl
import pigs.controller.PigController
import pigs.service.PigServiceImpl

import scala.io.StdIn

object PigProject extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher


  val pigDAOImpl = new PigDAOImpl
  val pigService = new PigServiceImpl(pigDAOImpl)
  val pigController = new PigController(pigService)


  private val routes: Route =
    pathPrefix("api" / "v1") {
      pigController.route
    }


  val bindingFuture = Http().bindAndHandle(routes, "localhost", 8081)

  println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
  println(s"Пример вызова метода http://localhost:8081/api/v1/pig/view/15")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
