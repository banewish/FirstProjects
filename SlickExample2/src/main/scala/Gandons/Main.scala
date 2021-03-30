package Gandons

import Gandons.dao.GandonDAOImpl
import Gandons.tables.Gandon
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val queryRunner = new QueryRunner(Database.forURL(
    url = "jdbc:postgresql://localhost:5432/gandon", // путь к БД. Он всегда стандартный, порт выбираем при установке
    user = "postgres", // стандартное имя пользователя при установке БД
    password = "1234", // пароль к БД
    driver = "org.postgresql.Driver")) // указываем, что мы используем базу данных postgresql

  val gandonDAOImpl = new GandonDAOImpl


  while (true) {

    val command: String = scala.io.StdIn.readLine("Введите команду ADD  VIEW_BY_ID  ALL:")

    command match {
      case "ADD" =>
        val gondonName: String = scala.io.StdIn.readLine("Введите name: ")
        val myGandon = Gandon(
          id = None,
          name = gondonName,
          size = 18,
          taste = true,
          price = 2.50)
        queryRunner.run(gandonDAOImpl.create(myGandon))

      case "VIEW_BY_ID" =>
        println("Введите id")
        val idToSearch: Int = scala.io.StdIn.readInt()
        println(queryRunner.run(gandonDAOImpl.findById(idToSearch)))

      case "ALL" =>
        println(queryRunner.run(gandonDAOImpl.all))

      case _ =>
        println("Idi v sraku")
    }

  }
}
