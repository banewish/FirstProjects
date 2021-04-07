package humanStatus.model

case class Human(id: Option[Int],fullName: FullName,workInfo: WorkInfo,education: Education,height:Int,weight:Int, race:String)

object HumanImplicits {




  import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
  import io.circe.{Decoder, Encoder}


  implicit val decoderEducation: Decoder[Education] = deriveDecoder[Education]
  implicit val encoderEducation: Encoder[Education] = deriveEncoder[Education]



  implicit val decoderFullName: Decoder[FullName] = deriveDecoder[FullName]
  implicit val encoderFullName: Encoder[FullName] = deriveEncoder[FullName]


  implicit val decoderWorkInfo: Decoder[WorkInfo] = deriveDecoder[WorkInfo]
  implicit val encoderWorkInfo: Encoder[WorkInfo] = deriveEncoder[WorkInfo]


  implicit val decoderHuman: Decoder[Human] = deriveDecoder[Human]
  implicit val encoderHuman: Encoder[Human] = deriveEncoder[Human]
}