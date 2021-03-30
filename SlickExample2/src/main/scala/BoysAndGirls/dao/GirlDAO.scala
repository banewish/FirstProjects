package BoysAndGirls.dao

import BoysAndGirls.tables.Girl
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

trait GirlDAO {

  def init: DBIO[Unit]

  def create(girl: Girl): DBIO[Int]

  def findById(id: Int): DBIO[Option[Girl]]

  def all: DBIO[Seq[Girl]]

}

class GirlDAOImpl(implicit executionContext: ExecutionContext) extends GirlDAO {

  import BoysAndGirls.tables.GirlModel.girls

  override def init = girls.schema.create

  override def create(girl: Girl): DBIO[Int] = girls += girl

  override def findById(id: Int): DBIO[Option[Girl]] = girls.filter(_.id ===id ).result.headOption

  override def all = girls.result
}