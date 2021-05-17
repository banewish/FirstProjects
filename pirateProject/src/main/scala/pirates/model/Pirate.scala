package pirates.model

case class Pirate(id: Option[Int], name: String, position: String, age:Int, height: Float, numberOfInjuries: Int, nameOfPapuga: Option[String], nationality: String)
object PirateImplicits {

  import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
  import io.circe.{Decoder, Encoder}

  implicit val decoder: Decoder[Pirate] = deriveDecoder[Pirate] // достает из джисона в кейс класс
  implicit val encoder: Encoder[Pirate] = deriveEncoder[Pirate]
}