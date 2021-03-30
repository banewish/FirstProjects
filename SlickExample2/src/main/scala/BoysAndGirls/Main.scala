package BoysAndGirls

import BoysAndGirls.dao.{BoyDAOImpl, GirlDAOImpl}
import BoysAndGirls.tables.{Boy, Girl}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val queryRunner = new QueryRunner(Database.forURL(
    url = "jdbc:postgresql://localhost:5432/boys",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver"))

  val girlDAOImpl = new GirlDAOImpl
  val boyDAOImpl = new BoyDAOImpl

  val myGirl = Girl(
    id = None,
    name = "Аграфина Куцепердык",
    gender = "Женщин",
    weight = 97
  )
  val myBoy = Boy(
    id = None,
    name = "Сергей Куцепердык",
    gender = "Мужчин",
    weight = 89 ,
    idForGirl = None
  )

  //queryRunner.run(boyDAOImpl.create(myBoy))
  //queryRunner.run(girlDAOImpl.create(myGirl))

  queryRunner.run(boyDAOImpl.setGirlById(boyId = 1, girlId = 1))


//  queryRunner.run(boyDAOImpl.init)
 // queryRunner.run(girlDAOImpl.init)

}
