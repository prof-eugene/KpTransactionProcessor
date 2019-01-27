package com.kp.payment

import com.kp.payment.result.PaymentResult

trait Payable {
  def getSumInCents:Int
  def getPaymentResult: Option[PaymentResult]
}
