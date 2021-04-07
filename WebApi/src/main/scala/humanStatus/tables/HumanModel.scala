package humanStatus.tables

import humanStatus.model.{Education, FullName, Human, WorkInfo}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

object HumanModel {

  class HumanTable(tag:Tag) extends Table[Human](tag,"vgfhfvtghjn"){
    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def firstName = column[String]("firstName")
    def middleName = column[Option[String]]("middleName")
    def lastName = column[String]("lastName")

    def position = column[String]("position")
    def salary = column[Int]("salary")


    def edc = column[String]("edc")
    def isRed = column[Boolean]("isRed")


    def height = column[Int]("height")
    def weight = column[Int]("weight")
    def race = column[String]("race")



    def fullName = (firstName, middleName, lastName).mapTo[FullName]
    def education = (edc,isRed).mapTo[Education]
    def workInfo = (position, salary).mapTo[WorkInfo]

    override def * = (id, fullName,workInfo, education,height,weight,race).mapTo[Human]

  }
  val humans= TableQuery[HumanTable]
}
