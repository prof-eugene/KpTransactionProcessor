package com.kp.payment.method

import com.kp.common.Loggable

case class MastercardPaymentMethod() extends PaymentMethod with Loggable{

  def makePayment(sumInCents: Int):Boolean = {
    log.info("Mastercard payment - started")
    log.info("Cents to pay: {}", sumInCents)
    Thread.sleep(PaymentMethod.processingMs)
    log.error("Mastercard payment - completed")
    true
  }

}
