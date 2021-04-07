package terraria.tables

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag
import terraria.model.Boss
import terraria.model.DropWeapon

object BossModel {


    implicit lazy val DropWeaponColumnType = MappedColumnType.base[Seq[DropWeapon], String](
      dropOrujia => ???,
      strWithJson => ???
    )


  class BossTable(tag: Tag) extends Table[Boss](tag, "boss") {
    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def hp = column[Int]("hp")

    def dmg = column[Int]("dmg")

    def canFly = column[Boolean]("canFly")

    def dropWeapon = column[Seq[DropWeapon]]("dropWeapon")

    override def * = (id, name, hp, dmg, canFly, dropWeapon).mapTo[Boss]
  }

  val bosses = TableQuery[BossTable]


}

