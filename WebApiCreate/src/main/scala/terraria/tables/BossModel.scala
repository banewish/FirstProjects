package terraria.tables

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag
import terraria.model.Boss


object BossModel {

  class BossTable(tag: Tag) extends Table[Boss](tag, "boss") {
    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def hp = column[Int]("hp")

    def dmg = column[Int]("dmg")

    def canFly = column[Boolean]("canFly")

    override def * = (id, name, hp, dmg, canFly).mapTo[Boss]
  }
val bosses = TableQuery[BossTable]
}

