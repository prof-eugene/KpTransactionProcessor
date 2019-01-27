package com.kp

import com.kp.common.Loggable
import com.kp.payment.method.PaymentMethod
import com.kp.process.{BagDeliveryProcessor, BagPaymentProcessor}
import com.kp.tool.BagGenerator
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**+
  * General class to represent flow and provide stats. Execution is asynchronous.
  */
class KpTransactionProcessor extends Loggable {
  def run(limit: Int) {
    val startTime = System.currentTimeMillis()

    log.info(s"start")

    log.info(s"Obtaining bag list from generator. Limit=$limit")
    val bagIterator = new BagGenerator(limit)

    log.info("Submitting bags for payment processing")
    val paidBags = new BagPaymentProcessor().process(bagIterator)

    log.info("Waiting when payments will be completed")
    val paidBagsList = Await.result(paidBags, Duration.Inf).toList

    log.debug("Intermediate paid bags dump:")
    paidBagsList.foreach(b => log.debug(b.toString))

    log.info("Submitting bags for delivery processing")
    val deliveryBags = new BagDeliveryProcessor().process(paidBagsList)

    log.info("Waiting results from delivery service")
    val deliveryBagsList = Await.result(deliveryBags, Duration.Inf).toList

    log.debug("Dumping bags:")
    deliveryBagsList.foreach(b => log.debug(b.toString))

    val stopTime = System.currentTimeMillis()
    val durationTime = stopTime - startTime
    log.info(f"Performance stats: bag count = $limit%d, expected sequential time = ${limit * PaymentMethod.processingMs}%d ms, fact time = $durationTime%d ms, speed rate = ${limit * PaymentMethod.processingMs.toFloat / durationTime}%1.2fx")

    log.info("stop")
  }
}
