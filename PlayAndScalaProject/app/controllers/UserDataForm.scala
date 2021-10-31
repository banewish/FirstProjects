package controllers

object UserDataForm {

  import play.api.data.Forms._
  import play.api.data._
  import play.api.data.validation.Constraints._

  case class UserData(password: String)

  val userFormConstraints = Form(
    mapping(
      "password" -> nonEmptyText
    )(UserData.apply)(UserData.unapply)
  )
}
