package pigs.DAO

import pigs.model.Pig
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext


trait PigDAO {

  def findById(id: Int): DBIO[Option[Pig]]

  def init: DBIO[Unit]

  def create(pig: Pig): DBIO[Int]

}

class PigDAOImpl(implicit executionContext: ExecutionContext) extends PigDAO {

  import pigs.model.PigModel.pigs

  override def init = pigs.schema.create

  override def create(pig: Pig): DBIO[Int] = pigs += pig

  override def findById(id: Int): DBIO[Option[Pig]] = {
    pigs.filter(_.id === id).result.headOption
  }


}