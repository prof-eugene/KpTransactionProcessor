package com.kp.client

import com.kp.payment.method.PaymentMethod

case class Client(id: String, paymentMethod: PaymentMethod) {}


object Client {
  val defaultPaymentMethod = PaymentMethod("Visa")

  def apply(): Client = new Client("", defaultPaymentMethod)
}