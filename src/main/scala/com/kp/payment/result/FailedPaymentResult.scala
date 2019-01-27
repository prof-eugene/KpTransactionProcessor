package com.kp.payment.result

/**+
  * Failed result of payment processing
  * @param error error messgae
  */
case class FailedPaymentResult(error: String) extends PaymentResult {

}
