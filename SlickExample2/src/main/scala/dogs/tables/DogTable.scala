package dogs.tables

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class Dog(id: Option[Int],
               name: String,
               poroda: String,
               gender: String,
               price: Double)

object DogModel {

  class DogTable(tag: Tag) extends Table[Dog](tag, "dog") {


    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def poroda = column[String]("poroda")
    def gender = column[String]("gender")
    def price = column[Double]("price")

    override def * = (id, name, poroda, gender, price).mapTo[Dog]
  }
  val dogs = TableQuery[DogTable]
}
