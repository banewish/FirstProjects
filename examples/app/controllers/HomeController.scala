package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with play.api.i18n.I18nSupport{

  def index() = Action { implicit request: Request[AnyContent] =>
    val passwordForm: Form[UserData] = Form(
          mapping(
            "password" -> number(min = 4, max = 15)
          )(UserData.apply)(UserData.unapply)
    )
      passwordForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.index(formWithErrors))
       },
        UserData => {
//          val newPassword = models.Password(UserData.password)
          Redirect(routes.HomeController.index())
        }
      )

    Ok(views.html.index(passwordForm))
  }

  @Singleton
  class Relative @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
    def helloview() = Action { implicit request =>
      Ok(views.html.hello()) // видимо нужно создать ещё что-то
    }
    def hello(password: Int) = Action {
      Ok(s"Hi, your password is good")
    }

  }
}
case class UserData(password: Int)
