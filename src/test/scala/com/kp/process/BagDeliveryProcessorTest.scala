package com.kp.process

import com.kp.bag.Bag
import com.kp.client.Client
import com.kp.delivery.{PendingDeliveryOrder, UnpaidDeliveryOrder}
import com.kp.payment.method.ExceptionPaymentMethod
import com.kp.payment.result.{FailedPaymentResult, SuccessfulPaymentResult}
import com.kp.tool.BagGenerator
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class BagDeliveryProcessorTest extends FunSuite {

  test("test delivery process - simple positive") {
    val limit = 10
    val bagIterator = new BagGenerator(limit, 0)
    val paidBags = new BagPaymentProcessor().process(bagIterator)
    val paidBagsList = Await.result(paidBags, Duration.Inf).toList
    val deliveryBags = new BagDeliveryProcessor().process(paidBagsList)
    val deliveryBagsList = Await.result(deliveryBags, Duration.Inf).toList

    assert(deliveryBagsList.size === limit)

    val bag = deliveryBagsList.head

    bag.getPaymentResult.get match {
      case SuccessfulPaymentResult(_, _) => assert(true)
      case _ => assert(false)
    }

    bag.getDeliveryOrder.get match {
      case PendingDeliveryOrder() => assert(true)
      case _ => assert(false)
    }
  }


  test("test delivery process - simple negative #1") {
    val limit = 10
    val bagIterator = new BagGenerator(limit, 0)
    // no payment!!!
    val paidBagsList = bagIterator.toList
    val deliveryBags = new BagDeliveryProcessor().process(paidBagsList)
    val deliveryBagsList = Await.result(deliveryBags, Duration.Inf).toList

    assert(deliveryBagsList.size === limit)

    val bag = deliveryBagsList.head

    assert(bag.getPaymentResult === None)
    bag.getDeliveryOrder.get match {
      case UnpaidDeliveryOrder() => assert(true)
      case _ => assert(false)
    }
  }


  test("test delivery process - simple negative #2") {

    val bagIterator = Seq(Bag("", Client("", ExceptionPaymentMethod())))
    // failed payment!!!
    val paidBags = new BagPaymentProcessor().process(bagIterator)
    val paidBagsList = Await.result(paidBags, Duration.Inf).toList
    val deliveryBags = new BagDeliveryProcessor().process(paidBagsList)
    val deliveryBagsList = Await.result(deliveryBags, Duration.Inf).toList

    assert(deliveryBagsList.size === 1)

    val bag = deliveryBagsList.head

    bag.paymentResult.get match {
      case FailedPaymentResult(_) => assert(true)
      case _ => assert(false)
    }

    bag.getDeliveryOrder.get match {
      case UnpaidDeliveryOrder() => assert(true)
      case _ => assert(false)
    }
  }
}
