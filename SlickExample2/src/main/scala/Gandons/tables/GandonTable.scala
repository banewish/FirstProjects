package Gandons.tables

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class Gandon(id: Option[Int],
                  name: String,
                  size: Int,
                  taste: Boolean,
                  price: Double)

object GandonModel {

  class GandonTable(tag: Tag) extends Table[Gandon](tag, "gandon") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def size = column[Int]("size")

    def taste = column[Boolean]("taste")

    def price = column[Double]("price")


    override def * = (id, name, size, taste, price).mapTo[Gandon]
  }

  val gandons = TableQuery[GandonTable]
}
