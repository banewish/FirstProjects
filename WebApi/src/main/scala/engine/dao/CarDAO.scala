package engine.dao


import engine.model.{Car, Engine}
import engine.tables.CarModel.cars
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

trait CarDAO {

  def findById(id: Int): DBIO[Option[Car]]

  def init: DBIO[Unit]

  def create(car: Car): DBIO[Int]

  def update(car: Car): DBIO[Int]

  def updateEngine(id: Int, engine: Engine): DBIO[Int]
}

class CarDAOImpl(implicit executionContext: ExecutionContext) extends CarDAO {
  override def init = cars.schema.create

  override def create(car: Car): DBIO[Int] = cars += car

  override def findById(id: Int): DBIO[Option[Car]] = {
    cars.filter(_.id === id).result.headOption
  }

  override def update(car: Car): DBIO[Int] = {
    cars.filter(_.id === car.id).update(car)
  }

  // сама база данных понимает, что все данные не нужны и достает ТОЛЬКО движок
  def findEngineById(id: Int): DBIO[Option[Engine]] = {
    cars.filter(_.id === id)
      .map(_.engine)
      .result.headOption
  }

  override def updateEngine(id: Int, engine: Engine): DBIO[Int] = {
    cars.filter(_.id === id).map(_.engine).update(engine)
  }
}