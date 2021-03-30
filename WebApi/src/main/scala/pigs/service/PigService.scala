package pigs.service

import pigs.model.Pig

import scala.concurrent.{ExecutionContext, Future}

trait PigService {
  def getPig(id: Int): Future[Pig]
}

class PigServiceImpl(implicit executionContext: ExecutionContext) extends PigService {

  override def getPig(id: Int): Future[Pig] = {
    Future(Pig(Some(id), "Турбо свин", 300))
  }

}

