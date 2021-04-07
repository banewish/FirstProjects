package engine.service
import engine.dao.CarDAO
import engine.model.{Car, Engine}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

trait CarService {

  def getCar(id: Int): Future[Car]

  def createCar(car: Car): Future[Int]

  def updateCar(car: Car): Future[Int]

  def getEngine(id: Int): Future[Engine]

  def updateEngine(carId: Int, engine: Engine): Future[Int]
}
class CarServiceImpl(carDAO: CarDAO)(implicit executionContext: ExecutionContext) extends CarService {
  val carDatabase = Database.forURL(
    url = "jdbc:postgresql://localhost:5432/car",
    user = "postgres",
    password = "1234",
    driver = "org.postgresql.Driver")

  override def getCar(id: Int): Future[Car] = {

    val optionCarInFuture: Future[Option[Car]] = carDatabase.run(carDAO.findById(id))
    optionCarInFuture.map(carOpt => carOpt.getOrElse(throw new Exception("invalid id: " + id)))
  }

  override def createCar(car: Car): Future[Int] = {
    println("i was here")
    carDatabase.run(carDAO.create(car))
  }

  override def updateCar(car: Car): Future[Int] = {
    carDatabase.run(carDAO.update(car))
  }

  // это если делать через сервис, что не правильно   - ведь мы достаем всю тачку, а это нагрузка. Нужно доставать только движок. Смотри метод findEngineById
  override def getEngine(id: Int): Future[Engine] = {
    val optionEngineInFuture: Future[Option[Engine]] =
      carDatabase.run(carDAO.findById(id)).map { carOpt => // развернет Future и положит в carOpt опциональную машину, если в фьюче будет Success
        carOpt match {
          case Some(car) => Some(car.engine)
          case None => None
        }
      }
    // or carDatabase.run(carDAO.findById(id)).map(carOpt => carOpt.map(car => car.engine))

    optionEngineInFuture.map(engineIOpt => engineIOpt.getOrElse(throw new Exception("invalid id: " + id)))
  }


  override def updateEngine(carId: Int, engine: Engine): Future[Int] = {
    carDatabase.run(carDAO.updateEngine(carId, engine))
  }

}