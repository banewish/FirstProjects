package models

import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ Future, ExecutionContext }


  class PasswordRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

    private val dbConfig = dbConfigProvider.get[JdbcProfile]

    import dbConfig._
    import profile.api._

    private class PasswordTable(tag: Tag) extends Table[PasswordHash](tag, "hashes") {

      def password_hash = column[String]("password_hash")

      def * = password_hash <> ((PasswordHash.apply _), PasswordHash.unapply)
    }

    private val passwords = TableQuery[PasswordTable]

//
//    def create(password_hash: String): Future[PasswordHash] = db.run {
//      passwords.map(p => (p.password_hash)
//
//        into (password_hash) => PasswordHash
//      ) += (password_hash )
//    }

def list(): Future[Seq[PasswordHash]] = db.run {
  passwords.result
}
  }