package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with play.api.i18n.I18nSupport {

  def index() = Action { implicit request: Request[AnyContent] =>

    Ok(views.html.index1())
  }
// в роутс создать один динамический парт, для всех пейджей. получить из роут и отправить в index1 . MVC!!!

def showPage(FirstNumber:Int, SecondNumber:Int) = Action {

  Ok("number of page is: " + (FirstNumber+SecondNumber))
}

}






