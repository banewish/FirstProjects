package AnimeTyans.tables

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class AnimeTyan(id: Option[Int],
               name: String,
               weight: Int,
               gender: Boolean,
               price: Double)

object AnimeTyanModel {

  class AnimeTyanTable(tag: Tag) extends Table[AnimeTyan](tag, "AnimeTyan") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def weight = column[Int]("weight")
    def gender = column[Boolean]("gender")
    def price = column[Double]("price")

    override def * = (id, name, weight, gender, price).mapTo[AnimeTyan]
  }
  val animeTyans = TableQuery[AnimeTyanTable]
}
