package pigs.tables

import pigs.model.Pig
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

object PigModel {

  class PigTable(tag: Tag) extends Table[Pig](tag, "pig") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def weight = column[Int]("weight")

    override def * = (id, name, weight).mapTo[Pig]
  }
  val pigs = TableQuery[PigTable]
}