package pigs.model

case class Pig(id: Option[Int], name: String, weight:Int)

// Как уже говорилось, Scala не умеет автоматически считывать данные из тела запроса в формате json в кейс класс,
// но так же, они и записывать кейс класс в джисон не умеет.

// Для этого на помощь приходят json библиотеки. Они есть разные и отличаются синтаксисом и что самое главное - временем работы
// Какие-то быстрей, другие медленее.
// Более медленные это как правило более старые библиотеки, но их продолжают иногда использовать

// Мы используем circe - одна из самых быстрых и новых либ.
// Помним, что нам надо из преобразовывать json -> case class
//                                       и case class -> json
// Все это можно сделать, обьявив две переменные для считывания и записи, а потом импортить их где надо

object PigImplicits {
  import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
  import io.circe.{Decoder, Encoder}

  implicit val decoder: Decoder[Pig] = deriveDecoder[Pig] // достает из джисона в кейс класс
  implicit val encoder: Encoder[Pig] = deriveEncoder[Pig] // записываем в джисон
}