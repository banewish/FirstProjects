package controllers

import models.{PasswordHash, PasswordRepository}

import java.lang.ProcessBuilder.Redirect
import javax.inject._
import play.api.mvc._
import play.api._
import play.api.libs.json.Json
import scala.concurrent._
import ExecutionContext.Implicits.global

class HomeController @Inject()(repo: PasswordRepository, cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

  import models.PasswordHash._
  import UserDataForm._

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def validate() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.registration(userFormConstraints))
  }

  def formPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    userFormConstraints.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.registration(formWithErrors))
      },
      formData => {
        Ok(formData.toString)
      }
    )
  }

  def getPasswords = Action.async { implicit request =>
    repo.list().map { passwords =>
      Ok(Json.toJson(passwords.toString()))
    }
  }
}
    // получить, хешировать, отправить на сервис и проверить наличие в базе
    // если есть - ошибка, если нет - добавить в базу


