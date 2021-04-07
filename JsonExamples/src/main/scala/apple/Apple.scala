package apple
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._


  import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
  import io.circe.{Decoder, Encoder}
object AppleExample extends App {


  case class Apple(color: String, weight: Double)


    val greenApple = Apple("green", 0.35)

//
//  val json = greenApple.asJson.noSpaces
// // println(json)
//
//  val decodedApple = decode[Apple](json)
//  //println(decodedApple)
//
//  val jsonInStr = "{\n  \"color\": \"Red\",\n  \"weight\": 0.431\n}"
//
//  val decodedRedApple = decode[Apple](jsonInStr)

  //println(decodedRedApple)

  implicit val decoder: Decoder[Apple] = deriveDecoder[Apple] // достает из джисона в кейс класс
  implicit val encoder: Encoder[Apple] = deriveEncoder[Apple] // записываем в джисон

val pizdec = Apple("grey", 22.8).asJson
println(pizdec)
}