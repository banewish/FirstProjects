package humanStatus.service
import humanStatus.dao.HumanDAO
import humanStatus.model.Human
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

trait HumanService {

  def getHuman(id: Int): Future[Human]

  def createHuman(human: Human): Future[Int]

  def updateHuman(human: Human): Future[Int]
}
class HumanServiceImpl(humanDAO: HumanDAO)(implicit executionContext: ExecutionContext) extends HumanService {
  val humanDatabase = Database.forURL(
    url = "jdbc:postgresql://localhost:5432/human",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver")

  override def getHuman(id: Int): Future[Human] = {

    val optionHumanInFuture: Future[Option[Human]] = humanDatabase.run(humanDAO.findById(id))
    optionHumanInFuture.map(humanOpt => humanOpt.getOrElse(throw new Exception("invalid id: " + id)))
  }

  override def createHuman(human: Human): Future[Int] = {
    println("i was here")
    humanDatabase.run(humanDAO.create(human))
  }

  override def updateHuman(human: Human): Future[Int] = {
    humanDatabase.run(humanDAO.update(human))
  }
}


