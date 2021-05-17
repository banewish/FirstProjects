package pigs.service

import dao.PigDAO
import pigs.model.Pig
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

trait PigService {
  def getPig(id: Int): Future[Pig]
}

class PigServiceImpl(pigDAO: PigDAO)(implicit executionContext: ExecutionContext) extends PigService {

  val pigDatabase = Database.forURL(
    url = "jdbc:postgresql://localhost:5432/pig",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver")


  override def getPig(id: Int): Future[Pig] = {
    val optionPigInFuture: Future[Option[Pig]] = pigDatabase.run(pigDAO.findById(id))

    optionPigInFuture.map(pigOpt => pigOpt.getOrElse(throw new Exception("no pig for id "+ id)))
  }

}

