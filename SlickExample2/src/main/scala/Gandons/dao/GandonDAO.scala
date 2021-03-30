package Gandons.dao

import Gandons.tables.Gandon
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

trait GandonDAO {



  def init: DBIO[Unit]

  def create(gandon: Gandon): DBIO[Int]

  def findById(id: Int): DBIO[Option[Gandon]]

  def all: DBIO[Seq[Gandon]]

}

class GandonDAOImpl(implicit executionContext: ExecutionContext) extends GandonDAO {

  import Gandons.tables.GandonModel.gandons

  override def init = gandons.schema.create

  override def create(gandon: Gandon): DBIO[Int] = gandons += gandon

  override def findById(id: Int): DBIO[Option[Gandon]] = {
    gandons.filter(_.id === id).result.headOption
  }

  override def all = gandons.result
}