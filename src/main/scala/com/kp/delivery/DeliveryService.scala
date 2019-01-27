package com.kp.delivery

import com.kp.bag.Bag
import com.kp.common.Loggable
import com.kp.payment.result.{FailedPaymentResult, SuccessfulPaymentResult}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**+
  * Service class to manage delivery order for paid bag
  */
case class DeliveryService() extends Loggable {

  def getDeliveryOrder(bag: Bag): Future[DeliveryOrder] = {
    log.info(s"Delivery service for bag.id = ${bag.id}")
    Future {
      bag.getPaymentResult match {
        case Some(i) => i match {
          case SuccessfulPaymentResult(_, _) => PendingDeliveryOrder()
          case FailedPaymentResult(_) => UnpaidDeliveryOrder()
        }
        case None => UnpaidDeliveryOrder()
      }
    }
  }
}
