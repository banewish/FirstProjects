package pigs

import pigs.dao.PigDAOImpl
import pigs.tables.Pig
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val queryRunner = new QueryRunner(Database.forURL(
    url = "jdbc:postgresql://localhost:5432/pig", // путь к БД. Он всегда стандартный, порт выбираем при установке
    user = "postgres", // стандартное имя пользователя при установке БД
    password = "1234", // пароль к БД
    driver = "org.postgresql.Driver")) // указываем, что мы используем базу данных postgresql

  val pigDAOImpl = new PigDAOImpl

  val myPig = Pig(
    id = None,
    name = "Турбо свин2",
    weight = 50,
    gender = true, // true - мужик, false - девушка
    price = 2.50
  )

  // комментируем одно из действий и запускаем

  queryRunner.run(pigDAOImpl.create(myPig))
  //queryRunner.run(pigDAOImpl.init)

}
