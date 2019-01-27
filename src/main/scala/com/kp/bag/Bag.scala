package com.kp.bag

import com.kp.client.Client
import com.kp.common.Loggable
import com.kp.delivery.{Deliverable, DeliveryOrder}
import com.kp.payment.Payable
import com.kp.payment.result.PaymentResult


case class Bag(id: String, client: Client, paymentResult: Option[PaymentResult] = None, deliveryOrder: Option[DeliveryOrder] = None) extends Loggable with Payable with Deliverable {
  val sumInCents: Integer = scala.util.Random.nextInt(100)


  override def getSumInCents: Int = sumInCents


  override def getPaymentResult: Option[PaymentResult] = paymentResult


  override def getDeliveryOrder: Option[DeliveryOrder] = deliveryOrder

}