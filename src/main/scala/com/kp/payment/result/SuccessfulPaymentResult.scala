package com.kp.payment.result


case class SuccessfulPaymentResult(rrn: String, authorization: String) extends PaymentResult {

}
