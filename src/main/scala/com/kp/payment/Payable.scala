package com.kp.payment

import com.kp.payment.result.PaymentResult

/**+
  * General trait to support Payable ability
  */
trait Payable {
  def getSumInCents:Int
  def getPaymentResult: Option[PaymentResult]
}
