package com.kp.client

import com.kp.payment.method.PaymentMethod

/**+
  * Client class to store information about client entity
  * @param id client Id
  * @param paymentMethod client's payment method
  */
case class Client(id: String, paymentMethod: PaymentMethod) {}


object Client {
  /**+
    * default payment method
    */
  val defaultPaymentMethod = PaymentMethod("Visa")

  /**+
    * default constructor without params
    * @return
    */
  def apply(): Client = new Client("", defaultPaymentMethod)
}