package terraria.model

case class Boss(id: Option[Int], name: String, hp: Int, dmg: Int, canFly: Boolean) {

}
object BossImplicit {

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

implicit val decoder: Decoder[Boss] = deriveDecoder[Boss] // достает из джисона в кейс класс
implicit val encoder: Encoder[Boss] = deriveEncoder[Boss] // записываем в джисон
}