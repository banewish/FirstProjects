package BoysAndGirls.tables

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class Boy(id: Option[Int],
               name: String,
               gender: String,
               weight: Int,
              idForGirl:Option[Int]
)

object BoyModel {

  class BoyTable(tag: Tag) extends Table[Boy](tag, "boy") {


    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def gender = column[String]("gender")
    def weight = column[Int]("weight")
    def idForGirl = column[Option[Int]]("idForGirl")

    override def * = (id, name, gender, weight,idForGirl).mapTo[Boy]
  }
  val boys = TableQuery[BoyTable]
}
