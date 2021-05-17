package pigs.model

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class Car(id: Option[Int], price: Double, model: String)

object Car {
  implicit val encoder: Encoder[Car] = deriveEncoder[Car]
  implicit val decoder: Decoder[Car] = deriveDecoder[Car]
}
