package apple


import io.circe.syntax._
  import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
  import io.circe.{Decoder, Encoder}

object AppleExample extends App {

  case class Apple(color: String, weight: Double, worm: Worm)

  case class Worm(name: String)


  implicit val decoder: Decoder[Apple] = deriveDecoder[Apple] // достает из джисона в кейс класс
  implicit val encoder: Encoder[Apple] = deriveEncoder[Apple] // записываем в джисон
  implicit val decoderWorm: Decoder[Worm] = deriveDecoder[Worm] // достает из джисона в кейс класс
  implicit val encoderWorm: Encoder[Worm] = deriveEncoder[Worm] // записываем в джисон
  val worm = Worm("Jonh-Bastrad-Fucking-Ass")
  val greenApple = Apple("green", 0.35, worm).asJson
  println(greenApple)

  println(greenApple.as[Apple])

  println(greenApple.as[Apple].toOption.get)
}
object ExampleFirstAndSecond extends App{
  case class D(a:String,b:Int)
  case class C(a:String,b:Int,c:Double)
  case class Number(a:Int,b:String,c:Double,d1: D,c1: C,seq:Seq[Int])


  implicit val decoderD: Decoder[D] = deriveDecoder[D] // достает из джисона в кейс класс
  implicit val encoderD: Encoder[D] = deriveEncoder[D] // записываем в джисон

  implicit val decoderC: Decoder[C] = deriveDecoder[C] // достает из джисона в кейс класс
  implicit val encoderC: Encoder[C] = deriveEncoder[C] // записываем в джисон

  implicit val decoderNumber: Decoder[Number] = deriveDecoder[Number] // достает из джисона в кейс класс
  implicit val encoderNumber: Encoder[Number] = deriveEncoder[Number] // записываем в джисон



  val d = D("jopa svnutri",123)
  val c = C("str",3,3.0)
  val number = Number(1,"jopa",2.76,d,c,Seq(1,2,3)).asJson
  println(number)
  println(number.as[Number])




}