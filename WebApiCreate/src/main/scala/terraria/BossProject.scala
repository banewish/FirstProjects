package terraria
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{pathPrefix, _}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import terraria.dao.BossDAOImpl
import terraria.controller.BossController
import terraria.service.BossServiceImpl
import scala.io.StdIn

object BossProject extends App {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher


  val bossDAOImpl = new BossDAOImpl()
  val bossService = new BossServiceImpl(bossDAOImpl)
  val bossController = new BossController(bossService)


  private val routes: Route =
    pathPrefix("api" / "v2") { // добавляем еще общую часть для контроллеров, нууу такая практика. Например, писать тут название приложения или версию
      bossController.route // добавляем пути из контроллера, для добавления сразу двух путей их нужно указать через знак ~ вместо запятой.
    }
  // вот мы указали тут часть пути и у нас есть метод создания, значит путь для него будет выглядеть /api/v2/pig/sozdat .
  // добавляем в начало наш хост и порт и получаем localhost:8081/api/v2/pig/sozdat



  val bindingFuture = Http().bindAndHandle(routes, "localhost", 8083) // стартуем сервер и говорит, что у нас есть вот такие вот контроллеры и пути (routes)

  println(s"Server online at http://localhost:8083/api/v2/boss/create/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}

