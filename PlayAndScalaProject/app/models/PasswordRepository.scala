package models

import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{Future, ExecutionContext}


class PasswordRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import controllers.HomeController
  import dbConfig._
  import profile.api._

  private class PasswordTable(tag: Tag) extends Table[PasswordHash](tag, "hashes") {

    def password_hash = column[String]("password_hash")

    def * = password_hash <> ((PasswordHash.apply _), PasswordHash.unapply _)
  }

  private val passwords = TableQuery[PasswordTable]


  def create(password_hash: String): Future[Int] = db.run {
    val queryForCreate =
    passwords.map(p => (p.password_hash)) += password_hash

    queryForCreate
  }

  def list(): Future[Seq[PasswordHash]] = db.run {
    passwords.result

  }
  def delete() : Future[Int] = db.run {
    passwords.filter(p => p.password_hash === "12345676")
      .delete
  }

}