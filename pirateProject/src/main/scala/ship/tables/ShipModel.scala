package ship.tables

import ship.model.Ship
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

object ShipModel {

  class ShipTable(tag: Tag) extends Table[Ship](tag, "ship") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def crew = column[Int]("crew")
    def captains_id = column[Int]("captains_id")

    override def * = (id, name, crew, captains_id).mapTo[Ship]
  }
  val ships = TableQuery[ShipTable]
}