package humanStatus

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{pathPrefix, _}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import humanStatus.controller.HumanController
import humanStatus.dao.HumanDAOImpl
import humanStatus.service.HumanServiceImpl

import scala.io.StdIn

object HumanStatusProject extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher


  val humanDAOImpl = new HumanDAOImpl
  val humanService = new HumanServiceImpl(humanDAOImpl)
  val humanController = new HumanController(humanService)


  private val routes: Route =
    pathPrefix("api" / "v2") {
      humanController.route
    }




  val bindingFuture = Http().bindAndHandle(routes, "localhost", 8081)

  println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())


}
