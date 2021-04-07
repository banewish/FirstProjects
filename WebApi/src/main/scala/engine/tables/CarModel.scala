package engine.tables




import engine.model.{Body, Car, Engine, Sheathing}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag


object CarModel {

  class CarTable(tag: Tag) extends Table[Car](tag, "car"){
    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def model = column[String]("model")
    def material= column[String]("material")
    def weightBody = column[Int]("weight_body")
    def priceBody = column[Int]("price_body")

    def power = column[Int]("power")
    def weightEngine = column[Int]("weight_engine")
    def priceEngine = column[Int]("price_engine")

    def weightSheathing = column[Int]("weight_sheathing")
    def priceSheathing = column[Int]("price_sheathing")

    def body = (model,material, weightBody, priceBody).mapTo[Body]
    def engine = (power, weightEngine, priceEngine).mapTo[Engine]
    def sheathing = (weightSheathing, priceSheathing).mapTo[Sheathing]

    override def * = (id,engine,body, sheathing).mapTo[Car]
  }
  val cars  = TableQuery[CarTable]
}
