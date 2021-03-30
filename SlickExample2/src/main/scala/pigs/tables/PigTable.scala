package pigs.tables

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class Pig(id: Option[Int],
               name: String,
               weight: Int,
               gender: Boolean, // true - мужик, false - девушка
               price: Double)

object PigModel { // место, где мы будем связывать кейс класс скалы с табличкой базы данных

  class PigTable(tag: Tag) extends Table[Pig](tag, "pig") {

    // тут мы связывает поля таблицы БД с переменным
    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def weight = column[Int]("weight")
    def gender = column[Boolean]("gender")
    def price = column[Double]("price")

    // склелили поля бд с кейс классом.
    override def * = (id, name, weight, gender, price).mapTo[Pig]
  }
  val pigs = TableQuery[PigTable]
}
