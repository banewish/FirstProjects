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
  import models.PasswordRepository

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def validate() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.registration(userFormConstraints))
  }

  def formPost() = Action.async { implicit request =>
    userFormConstraints.bindFromRequest.fold(
      formWithErrors => {
        Future.successful(Ok(views.html.registration(formWithErrors)))
      },
      formData => {
        repo.create(formData.password).map { _ =>
          Redirect(routes.HomeController.index)
        }
      }
    )
  }

  def getPassword = Action.async { implicit request =>
    repo.list().map { passwords =>
      Ok(Json.toJson(passwords.toString()))
    }
  }

  def deletePassword = Action.async { implicit request =>
    repo.delete().map { passwords =>
      Ok(Json.toJson(passwords.toString()))
    }
  }
}

// получить, хешировать, отправить на сервис и проверить наличие в базе
// если есть - ошибка, если нет - добавить в базу


