package BoysAndGirls.dao

import BoysAndGirls.tables.Boy
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

trait BoyDAO {

  def init: DBIO[Unit]
  def create(boy: Boy): DBIO[Int]
  def findById(id: Int): DBIO[Option[Boy]]
  def all: DBIO[Seq[Boy]]
  def setGirlById(boyId: Int, girlId: Int): DBIO[Int]

}

class BoyDAOImpl(implicit executionContext: ExecutionContext) extends BoyDAO {

  import BoysAndGirls.tables.BoyModel.boys

  override def init = boys.schema.create

  override def create(boy: Boy): DBIO[Int] = boys += boy

  override def findById(id: Int): DBIO[Option[Boy]] = {
    boys.filter(_.id === id).result.headOption
  }
  override def all = boys.result

  override def setGirlById(boyId: Int, girlId: Int): DBIO[Int] = {
    boys.filter(_.id === boyId)
      .map(b => (b.idForGirl))
      .update(Some(girlId))
  }
}