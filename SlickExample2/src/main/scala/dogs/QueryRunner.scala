package dogs

import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration



class QueryRunner(database: JdbcProfile#Backend#DatabaseDef) { // database настройки для подключения к базе данных


  def run[R](a: DBIOAction[R, NoStream, Nothing]): R = {
    Await.result(database.run(a), Duration.Inf)
  }
}