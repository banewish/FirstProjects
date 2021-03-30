package pigs.dao
import pigs.tables.{Pig, PigModel}
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

// Этот файл нужен для формирования запроса. Можно сказать, вот тут слик генерил SQL код, который чет делает

// Такие файлы всегда называют по формату *название сущности* + DAO
// Используют трейт, потому что иногда надо ходить в несколько баз и наследники могут делать это по разному
trait PigDAO {

  // DBIO - это как Future, только для базы данных. Да БД захотела свой собстенный тип.

  def init: DBIO[Unit]

  def create(pig: Pig): DBIO[Int]

}

// Файлы всегда называют по формату *название трейта* + Impl . (Impl = implementation = реализация)
class PigDAOImpl(implicit executionContext: ExecutionContext) extends PigDAO {

  import PigModel.pigs

  override def init = pigs.schema.create

  override def create(pig: Pig): DBIO[Int] = pigs += pig

}