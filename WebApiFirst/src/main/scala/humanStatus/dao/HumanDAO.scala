package humanStatus.dao



import humanStatus.model.Human
import humanStatus.tables.HumanModel.humans
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

trait HumanDAO {

    def findById(id: Int): DBIO[Option[Human]]

    def init: DBIO[Unit]

    def create(human: Human): DBIO[Int]

    def update(human: Human): DBIO[Int]
}

class HumanDAOImpl(implicit executionContext: ExecutionContext) extends HumanDAO{
  override def init = humans.schema.create

  override def create(human: Human): DBIO[Int] = humans += human

  override def findById(id: Int): DBIO[Option[Human]] = {
    humans.filter(_.id === id).result.headOption
  }

  override def update(human: Human): DBIO[Int] = {
    humans.filter(_.id === human.id).update(human)
  }



}