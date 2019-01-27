package com.kp.bag

import com.kp.client.Client
import com.kp.common.Loggable
import com.kp.delivery.{Deliverable, DeliveryOrder}
import com.kp.payment.Payable
import com.kp.payment.result.PaymentResult

/**+
  * Object represents a bag that contains
  * @param id bag id
  * @param client reference to client
  * @param paymentResult instance of payment processing
  * @param deliveryOrder instance of delivery order
  */

case class Bag(id: String, client: Client, paymentResult: Option[PaymentResult] = None, deliveryOrder: Option[DeliveryOrder] = None) extends Loggable with Payable with Deliverable {
  val sumInCents: Integer = scala.util.Random.nextInt(100)


  /**+
    * Cost of the bag to be paid
    * @return amount of cents
    */
  override def getSumInCents: Int = sumInCents

  /**+
    *
    * @return current status of payment processing
    */
  override def getPaymentResult: Option[PaymentResult] = paymentResult

  /**+
    *
    * @return current status of delivery
    */
  override def getDeliveryOrder: Option[DeliveryOrder] = deliveryOrder

}