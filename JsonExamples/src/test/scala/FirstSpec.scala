import io.circe.{Decoder, Encoder, Json}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps
import org.scalatest.Suite
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FirstSpec extends AnyFlatSpec with Matchers with Suite {


  "One" should "be equal 1 " in {
    1 shouldBe 1
  }


  "toUpperCase" should "work correctly" in {
    val testString = "Hello world"
    val expectedResult = "HELLO WORLD"

    testString.toUpperCase shouldBe expectedResult
  }

"Two" should "be equal 2"  in {
  case class A(b: String)

  implicit val decoderD: Decoder[A] = deriveDecoder[A] // достает из джисона в кейс класс
  implicit val encoderD: Encoder[A] = deriveEncoder[A] // записываем в джисон
  val a:Json = A("Sraka").asJson
  val expectResult: String = "{\n  \"b\" : \"Sraka\"\n}"

  a.toString() shouldBe expectResult // одно должно равняться другому
}
  "Zero TWO" should "be equal 02" in {
    def upperMrName(name: String): String = "Mr. " + name.toUpperCase
    upperMrName("валерка") shouldBe "Mr. ВАЛЕРКА"
  }

  "true" should "true" in {
    def someMethod(something: Boolean): Unit =
      if (something == true) true
      else throw new Exception("not true")


    someMethod(true) shouldBe(true)
  }

  "false" should "false" in {
    def someMethod(something: Boolean): Unit =
      if (something == true) true
      else
        throw new Exception("not true")

val thrown = the [Exception] thrownBy someMethod(false) // ловит ошибку и возвращает без красной херни в ебало
 thrown.getMessage should equal("not true")
  }

  "summary" should "be correctly" in {
    def sum(a:Int, b: Int) = a + b
      sum(18, 12) shouldBe(30)

  }
  "multiply" should "be correctly" in {
    def mlp(a:Int, b: Int) = a * b
  mlp(5,5) shouldBe(25)

}
"maslina" should "be taken" in {
  def maslina: String = throw new Exception("Бля, я маслину поймал")
  val thrown = the [Exception] thrownBy maslina

  thrown.getMessage should equal ("Бля я маслину поймал")
}
"it" should "be correctly" in {
  case class A(jopa: String)
  case class B(id: Int, spisokJop: Seq[A])

  implicit val decoderA: Decoder[A] = deriveDecoder[A] // достает из джисона в кейс класс
  implicit val encoderA: Encoder[A] = deriveEncoder[A] // записываем в джисон

  implicit val decoderB: Decoder[B] = deriveDecoder[B] // достает из джисона в кейс класс
  implicit val encoderB: Encoder[B] = deriveEncoder[B] // записываем в джисон
  val a1 = A("jopa1")
  val a2 = A("jopa2")
  val a3= A("jopa3")
  val a4 = A("jopa4")

  val b = B(12, Seq(a1,a2,a3,a4)).asJson
 b shouldBe "{\n  \"id\" : 12,\n  \"spisokJop\" : [\n    {\n      \"jopa\" : \"jopa1\"\n    }" +
   ",\n    {\n      \"jopa\" : \"jopa2\"\n    },\n    {\n      \"jopa\" : \"jopa3\"\n    }" +
   ",\n    {\n      \"jopa\" : \"jopa4\"\n    }\n  ]\n}"
}
}