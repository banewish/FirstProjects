package Gandons

import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration

// Помнишь надо было нажать кнопочку run для того, чтобы запустить sql ?
// Скале тоже нужно нажимать эту кнопку (посылает команду на выполнение)

class QueryRunner(database: JdbcProfile#Backend#DatabaseDef) { // database настройки для подключения к базе данных

  // R - это неопределенный тип., Т.е. вместо него может быть любой. О
  // а - название параметра
  // DBIOAction[R, NoStream, Nothing] - это тип запроса в программном коде. В pgAdmin у нас это строка,
  // а тут такая кракозябра.

  def run[R](a: DBIOAction[R, NoStream, Nothing]): R = {
    Await.result(database.run(a), Duration.Inf)
  }
}