package com.kp.payment.result

/**+
  * Successful result of payment processing
  * @param rrn RRN code
  * @param authorization authorization code
  */
case class SuccessfulPaymentResult(rrn: String, authorization: String) extends PaymentResult {

}
