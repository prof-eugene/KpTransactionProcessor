package com.kp.process

import com.kp.bag.Bag
import com.kp.common.Loggable
import com.kp.delivery.DeliveryService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**+
  * Process to manage delivery orders for paid bags
  */
class BagDeliveryProcessor extends Loggable {
  def process(bags: TraversableOnce[Bag]): Future[TraversableOnce[Bag]] = {

    val result = Future.traverse(bags) {
      bag =>
        DeliveryService().getDeliveryOrder(bag) map {
          d => bag.copy(deliveryOrder = Some(d))
        }
    }

    result.onComplete({
      case Success(_) => log.info("Payment queue completed")
      case Failure(e) => log.error(s"Error processing future operations, error = ${e.getMessage}")
    })

    result
  }
}
