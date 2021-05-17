package ship.dao

import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._
import ship.model.Ship
import scala.concurrent.ExecutionContext


trait ShipDAO {

  def findById(id: Int): DBIO[Option[Ship]]

  def init: DBIO[Unit]

  def create(ship: Ship): DBIO[Int]

  def update(ship: Ship): DBIO[Int]
}

class ShipDAOImpl(implicit executionContext: ExecutionContext) extends ShipDAO {

  import ship.tables.ShipModel.ships

  override def init = ships.schema.create

  override def create(ship: Ship): DBIO[Int] = ships += ship

  // headOption говорит, что нам надо забрать 1ю запись, удовлетворающую условию
  // программа же не знает 1 там запись или много. Без headOption вы бы получили Seq()
  // потому что чисто формально может быть много записей с одинаковым айди
  // а так, получаем всего лишь Option, ведь данных может и не быть вовсе)ь
  override def findById(id: Int): DBIO[Option[Ship]] = {
    ships.filter(_.id === id).result.headOption
  }

  override def update(ship: Ship): DBIO[Int] = {
    ships.filter(_.id === ship.id).update(ship)
  }

}