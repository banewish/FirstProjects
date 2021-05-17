package pirates.dao


import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._
import pirates.model.Pirate
import scala.concurrent.ExecutionContext


trait PirateDAO {

  def findById(id: Int): DBIO[Option[Pirate]]

  def init: DBIO[Unit]

  def create(pirate: Pirate): DBIO[Int]

  def update(pirate: Pirate): DBIO[Int]
}

class PirateDAOImpl(implicit executionContext: ExecutionContext) extends PirateDAO {

  import pirates.tables.PirateModel.pirates

  override def init = pirates.schema.create

  override def create(pirate: Pirate): DBIO[Int] = pirates+= pirate

  // headOption говорит, что нам надо забрать 1ю запись, удовлетворающую условию
  // программа же не знает 1 там запись или много. Без headOption вы бы получили Seq()
  // потому что чисто формально может быть много записей с одинаковым айди
  // а так, получаем всего лишь Option, ведь данных может и не быть вовсе)ь
  override def findById(id: Int): DBIO[Option[Pirate]] = {
    pirates.filter(_.id === id).result.headOption
  }

  override def update(pirate: Pirate): DBIO[Int] = {
    pirates.filter(_.id === pirate.id).update(pirate)
  }

}

