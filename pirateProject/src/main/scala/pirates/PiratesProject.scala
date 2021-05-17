package pirates

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{pathPrefix, _}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import pirates.dao.{PirateDAO, PirateDAOImpl}
import pirates.controller.PirateController
import pirates.service.PirateServiceImpl

import scala.io.StdIn

object PiratesProject extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher


  val pirateDAOImpl = new PirateDAOImpl
  val pirateService = new PirateServiceImpl(pirateDAOImpl)
  val pirateController = new PirateController(pirateService)


  private val routes: Route =
    pathPrefix("api" / "v1") {
      pirateController.route
    }


  val bindingFuture = Http().bindAndHandle(routes, "localhost", 8081)

  println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")

  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
