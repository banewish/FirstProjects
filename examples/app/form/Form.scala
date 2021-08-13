package form
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc.Results.Redirect


case class UserData(password: Int)

class Form {

  val userForm = Form(
    mapping(
      "password" -> number
    )(UserData.apply)(UserData.unapply)

  )
  val userFormConstraints2 = Form(
    mapping(
      "password" -> number(min = 4, max = 15)
    )(UserData.apply)(UserData.unapply)
  )
  val anyData = Map("password" -> "211235")
  val userData = userForm.bind(anyData).get
  userForm.bindFromRequest(POST).fold()
  userData => {
    /* binding success, you get the actual value. */
    val newUser = models.User(userData.password)
    val id      = models.User.create(newUser)
    Redirect(routes.Application.home(id))

}
}







