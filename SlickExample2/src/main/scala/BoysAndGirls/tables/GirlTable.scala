package BoysAndGirls.tables

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

case class Girl(id: Option[Int],
               name: String,
               gender: String,
               weight: Int)

object GirlModel {

  class GirlTable(tag: Tag) extends Table[Girl](tag, "girl") {

    def id = column[Option[Int]]("idGirl", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def gender = column[String]("gender")
    def weight = column[Int]("weight")

    override def * = (id, name, gender, weight).mapTo[Girl]
  }
  val girls = TableQuery[GirlTable]
}
