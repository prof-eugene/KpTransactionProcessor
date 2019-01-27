package com.kp.payment.method

import com.kp.common.Loggable

case class FailPaymentMethod() extends PaymentMethod with Loggable {
  override def makePayment(sumInCents: Int): Boolean = false
}
