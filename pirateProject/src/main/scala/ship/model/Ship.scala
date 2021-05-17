package ship.model



case class Ship(id: Option[Int], name: String, crew: Int, captains_id: Int)
object PigImplicits {

  import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
  import io.circe.{Decoder, Encoder}

  implicit val decoder: Decoder[Ship] = deriveDecoder[Ship] // достает из джисона в кейс класс
  implicit val encoder: Encoder[Ship] = deriveEncoder[Ship]
}
