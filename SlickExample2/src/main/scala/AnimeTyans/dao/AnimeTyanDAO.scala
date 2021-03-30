package AnimeTyans.dao

import AnimeTyans.tables.AnimeTyan
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

trait AnimeTyanDAO {


  def init: DBIO[Unit]

  def create(animeTyan: AnimeTyan): DBIO[Int]

}

class AnimeTyanDAOImpl(implicit executionContext: ExecutionContext) extends AnimeTyanDAO {

import AnimeTyans.tables.AnimeTyanModel.animeTyans

  override def init = animeTyans.schema.create

  override def create(animeTyan: AnimeTyan): DBIO[Int] = animeTyans += animeTyan

}