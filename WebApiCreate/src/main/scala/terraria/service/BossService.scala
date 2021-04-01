package terraria.service

import terraria.dao.BossDAO
import terraria.model.Boss
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{ExecutionContext, Future}


trait BossService {
  def getBoss(id: Int): Future[Boss]
  def createBoss(boss: Boss): Future[Int]
  def updateBoss(boss: Boss): Future[Int]
}
class BossServiceImpl(bossDAO: BossDAO)(implicit executionContext: ExecutionContext) extends BossService {

  val bossDatabase = Database.forURL(
    url = "jdbc:postgresql://localhost:5432/boss",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver")

    override def getBoss(id: Int): Future[Boss] = {
      val optionBossInFuture: Future[Option[Boss]] = bossDatabase.run(bossDAO.findById(id))
      optionBossInFuture.map(bossOpt => bossOpt.getOrElse(throw new Exception("invalid id " + id)))
    }

    override def createBoss(boss: Boss): Future[Int] = {
      bossDatabase.run(bossDAO.create(boss))
    }

    override def updateBoss(boss: Boss): Future[Int] = {
      bossDatabase.run(bossDAO.update(boss))
    }

}

