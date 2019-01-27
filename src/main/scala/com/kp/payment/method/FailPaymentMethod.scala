package com.kp.payment.method

import com.kp.common.Loggable

/**+
  * Payment method that always fails makePayment call
  */
case class FailPaymentMethod() extends PaymentMethod with Loggable {
  override def makePayment(sumInCents: Int): Boolean = false
}
