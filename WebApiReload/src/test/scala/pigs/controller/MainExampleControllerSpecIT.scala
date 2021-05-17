package pigs.controller

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.util.ByteString
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.semiauto.deriveDecoder
import io.circe.{Decoder, Encoder}
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterAll, Suite}
import pigs.CarProject

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class MainExampleControllerSpecIT extends AsyncFlatSpec with Suite with Matchers with BeforeAndAfterAll with ScalaFutures with Eventually {

  "/example/raw/helloWorld" should "должен вощврощать нормальный результат" in {
    implicit val system = ActorSystem()

    val resultFuture = Http()
      .singleRequest(
        HttpRequest(
          method = HttpMethods.GET,
          uri = "http://localhost:8080/api/v1/example/raw/helloWorld",
          //entity = HttpEntity.Strict(ContentTypes.`application/json`, entityToByteString()// если надо в запросе передать тело то передаем его тут в метод entityToByteString
        )
      )

    resultFuture.map { result =>
      result.status shouldBe StatusCodes.OK
      Await.result(Unmarshal(result).to[String], Duration.Inf) shouldBe "hello world" //  к сожалению, процесс доставания результата тоже возвр. Future, по этому мы ждем результата через Await.result
    }
  }

  "/example/json/helloWorld" should "должен вощврощать нормальный результат" in {
    implicit val system = ActorSystem()

    val resultFuture = Http()
      .singleRequest(
        HttpRequest(
          method = HttpMethods.GET,
          uri = "http://localhost:8080/api/v1/example/json/helloWorld",
        )
      )

    resultFuture.map { result =>
      result.status shouldBe StatusCodes.OK
      case class StrInJsonCopy(str: String)
      implicit val decoder: Decoder[StrInJsonCopy] = deriveDecoder[StrInJsonCopy]
      Await.result(Unmarshal(result).to[StrInJsonCopy], Duration.Inf) shouldBe StrInJsonCopy("hello world")
    }
  }


  def entityToByteString[T](t: T)(implicit encoder: Encoder[T]): ByteString = {
    ByteString(encoder.apply(t).toString())
  }

  override def beforeAll(): Unit = {
    val main = new CarProject // Создаем класс, который запускает приложение
    Await.result(main.start, Duration.Inf) // Метод запуска возвращает Future, по этому мы ждем запуска при помощи Await.result
  }
}
