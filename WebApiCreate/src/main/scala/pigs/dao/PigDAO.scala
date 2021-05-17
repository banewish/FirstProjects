package pigs.dao

import pigs.model.Pig
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext


trait PigDAO {

  def findById(id: Int): DBIO[Option[Pig]]

  def init: DBIO[Unit]

  def create(pig: Pig): DBIO[Int]

  def update(pig: Pig): DBIO[Int]
}

class PigDAOImpl(implicit executionContext: ExecutionContext) extends PigDAO {

  import pigs.tables.PigModel.pigs

  override def init = pigs.schema.create

  override def create(pig: Pig): DBIO[Int] = pigs += pig

  // headOption говорит, что нам надо забрать 1ю запись, удовлетворающую условию
  // программа же не знает 1 там запись или много. Без headOption вы бы получили Seq()
  // потому что чисто формально может быть много записей с одинаковым айди
  // а так, получаем всего лишь Option, ведь данных может и не быть вовсе)ь
  override def findById(id: Int): DBIO[Option[Pig]] = {
    pigs.filter(_.id === id).result.headOption
  }

  override def update(pig: Pig): DBIO[Int] = {
    pigs.filter(_.id === pig.id).update(pig)
  }


}


