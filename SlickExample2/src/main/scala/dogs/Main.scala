package dogs


import dogs.dao.DogDAOImpl
import dogs.tables.Dog
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {



  val queryRunner = new QueryRunner(Database.forURL(
    url = "jdbc:postgresql://localhost:5432/dogs",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver"))

  val dogDAOImpl = new DogDAOImpl




  while (true) {

    val command: String = scala.io.StdIn.readLine("Введите команду ADD  VIEW_BY_ID  ALL:")

    command match {
      case "ADD" =>
        val dognName: String = scala.io.StdIn.readLine("Введите name: ")
        val dogPoroda: String = scala.io.StdIn.readLine("Введите poroda: ")
        val dogGender: String = scala.io.StdIn.readLine("Введите gender: ")
        println("введите price: ")
        val dogPrice: Double = scala.io.StdIn.readDouble()
        val myDog = Dog(
          id = None,
          name = dognName,
          poroda = dogPoroda,
          gender = dogGender,
          price = dogPrice)
        queryRunner.run(dogDAOImpl.create(myDog))

      case "VIEW_BY_ID" =>
        println("Введите id")
        val idToSearch: Int = scala.io.StdIn.readInt()
        println(queryRunner.run(dogDAOImpl.findById(idToSearch)))

      case "ALL" =>
        println(queryRunner.run(dogDAOImpl.all))

      case _ =>
        println("Idi v sraku")
    }

  }


/*  queryRunner.run(dogDAOImpl.init)*/

}
