package com.kp.payment.method

import com.kp.common.Loggable

case class VisaPaymentMethod() extends PaymentMethod with Loggable {

  def makePayment(sumInCents: Int):Boolean = {
    log.info("Visa payment - started")
    log.info("Cents to pay: {}", sumInCents)
    Thread.sleep(PaymentMethod.processingMs)
    log.info("Visa payment - completed")
    true
  }
}
