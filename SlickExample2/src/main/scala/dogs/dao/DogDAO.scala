package dogs.dao

import dogs.tables.Dog
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext


trait DogDAO {



  def init: DBIO[Unit]

  def create(dog: Dog): DBIO[Int]

  def findById(id: Int): DBIO[Option[Dog]]

  def all: DBIO[Seq[Dog]]

}

class DogDAOImpl(implicit executionContext: ExecutionContext) extends DogDAO {

  import dogs.tables.DogModel.dogs

  override def init = dogs.schema.create

  override def create(dog: Dog): DBIO[Int] = dogs += dog

  override def findById(id: Int): DBIO[Option[Dog]] = {
    dogs.filter(_.id === id).result.headOption
  }
  override def all = dogs.result
}