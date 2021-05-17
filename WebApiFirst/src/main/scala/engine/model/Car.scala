package engine.model

case class Car(id: Option[Int], engine: Engine, body: Body, sheathing: Sheathing)


  object CarImplicits {

    import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
    import io.circe.{Decoder, Encoder}


    implicit val decoderEngine: Decoder[Engine] = deriveDecoder[Engine]
    implicit val encoderEngine: Encoder[Engine] = deriveEncoder[Engine]



    implicit val decoderBody: Decoder[Body] = deriveDecoder[Body]
    implicit val encoderBody: Encoder[Body] = deriveEncoder[Body]


    implicit val decoderSheathing: Decoder[Sheathing] = deriveDecoder[Sheathing]
    implicit val encoderSheathing: Encoder[Sheathing] = deriveEncoder[Sheathing]

    implicit val decoderCar: Decoder[Car] = deriveDecoder[Car]
    implicit val encoderCar: Encoder[Car] = deriveEncoder[Car]
  }

