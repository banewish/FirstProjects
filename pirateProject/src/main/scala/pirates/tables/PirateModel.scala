package pirates.tables

import pirates.model.Pirate
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

object PirateModel {

  class PirateTable(tag: Tag) extends Table[Pirate](tag, "pirate") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def position = column[String]("position")
    def age = column[Int]("age")
    def height = column[Float]("height")
    def numberOfInjuries = column[Int]("numberOfInjuries")
    def nameOfPapuga = column[Option[String]]("nameOfPapuga")
    def nationality = column[String]("nationality")

    override def * = (id, name, position, age, height, numberOfInjuries, nameOfPapuga, nationality).mapTo[Pirate]
  }
  val pirates = TableQuery[PirateTable]
}