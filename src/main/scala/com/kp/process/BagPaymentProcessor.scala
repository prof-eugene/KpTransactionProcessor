package com.kp.process

import com.kp.bag.Bag
import com.kp.common.Loggable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

class BagPaymentProcessor extends Loggable {
  def process(bags: TraversableOnce[Bag]): Future[TraversableOnce[Bag]] = {

    val result = Future.traverse(bags) { bag =>
      bag.client.paymentMethod.pay(bag) map {
        result => bag.copy(paymentResult = Some(result))
      }
    }

    result.onComplete({
      case Success(_) => log.info("Payment queue completed")
      case Failure(e) => log.error(s"Error processing future operations, error = ${e.getMessage}")
    })

    result
  }
}
