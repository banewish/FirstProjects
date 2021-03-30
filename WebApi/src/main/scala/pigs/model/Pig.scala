package pigs.model
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag


case class Pig(id: Option[Int], name: String,weight:Int)

object PigModel {

  class PigTable(tag: Tag) extends Table[Pig](tag, "pig") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def weight = column[Int]("weight")


    override def * = (id, name, weight).mapTo[Pig]
  }
  val pigs = TableQuery[PigTable]
}