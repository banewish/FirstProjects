package terraria.dao

import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext
import terraria.model.Boss



  trait BossDAO {

    def findById(id: Int): DBIO[Option[Boss]]

    def init: DBIO[Unit]

    def create(boss: Boss): DBIO[Int]

    def update(boss: Boss): DBIO[Int]
  }

  class BossDAOImpl(implicit executionContext: ExecutionContext) extends BossDAO {

    import terraria.tables.BossModel.bosses

    override def init = bosses.schema.create

    override def create(boss: Boss): DBIO[Int] = bosses += boss

    override def findById(id: Int): DBIO[Option[Boss]] = {
      bosses.filter(_.id === id).result.headOption
    }

    override def update(boss: Boss): DBIO[Int] = {
      bosses.filter(_.id === boss.id).update(boss)
    }

}