package com.kp.payment.method

import com.kp.common.Loggable

/**+
  * Payment method that always throws an exception
  */
case class ExceptionPaymentMethod() extends PaymentMethod with Loggable {
  override def makePayment(sumInCents: Int): Boolean = throw new Exception("PaymentMethod exception")
}
