package models


import java.lang.ProcessBuilder.Redirect
import javax.inject._
import play.api.mvc._
import play.api._
import play.api.libs.json.Json
import scala.concurrent._
import ExecutionContext.Implicits.global

import play.api.libs.json.{Json, OFormat}


case class PasswordHash(password_hash: String) {
  implicit val passwordFormat: OFormat[PasswordHash] = Json.format[PasswordHash]


}


