package pirates.service

import pirates.dao.PirateDAO
import pirates.model.Pirate
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

trait PirateService {
  def getPirate(id: Int): Future[Pirate]

  def createPirate(pirate: Pirate): Future[Int]

  def updatePirate(pirate: Pirate): Future[Int]

  def init: Future[Unit]
}

class PirateServiceImpl(pirateDAO: PirateDAO)(implicit executionContext: ExecutionContext) extends PirateService {

  val pirateDatabase = Database.forURL(
    url = "jdbc:postgresql://localhost:5432/pirate",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver")

  override def getPirate(id: Int): Future[Pirate] = {
    val optionPirateInFuture: Future[Option[Pirate]] = pirateDatabase.run(pirateDAO.findById(id))
    optionPirateInFuture.map(pirateOpt => pirateOpt.getOrElse(throw new Exception("no pirate for id " + id)))
  }

  override def createPirate(pirate: Pirate): Future[Int] = {
    pirateDatabase.run(pirateDAO.create(pirate))
  }

  override def updatePirate(pirate: Pirate): Future[Int] = {
    pirateDatabase.run(pirateDAO.update(pirate))
  }

  override def init: Future[Unit] = {
    pirateDatabase.run(pirateDAO.init)
  }
}
