package AnimeTyans

import AnimeTyans.dao.AnimeTyanDAOImpl
import AnimeTyans.tables.AnimeTyan
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val queryRunner = new QueryRunner(Database.forURL(
    url = "jdbc:postgresql://localhost:5432/pig",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver"))

  val animeTyanDAOImpl = new AnimeTyanDAOImpl

  val myAnimeTyan = AnimeTyan(
    id = None,
    name = "Аниме тяночка",
    weight = 50,
    gender = true,
    price = 2.50
  )

  // комментируем одно из действий и запускаем

  queryRunner.run(animeTyanDAOImpl.create(myAnimeTyan))
  //queryRunner.run(animeTyanDAOImpl.init)

}
