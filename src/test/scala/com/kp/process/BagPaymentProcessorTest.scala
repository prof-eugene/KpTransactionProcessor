package com.kp.process

import com.kp.bag.Bag
import com.kp.client.Client
import com.kp.delivery.PendingDeliveryOrder
import com.kp.payment.method.FailPaymentMethod
import com.kp.payment.result.{FailedPaymentResult, SuccessfulPaymentResult}
import com.kp.tool.BagGenerator
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class BagPaymentProcessorTest extends FunSuite {

  test("test payment process - simple positive") {
    val limit = 10
    val bagIterator = new BagGenerator(limit, 0.5)
    val paidBags = new BagPaymentProcessor().process(bagIterator)
    val paidBagsList = Await.result(paidBags, Duration.Inf).toList

    assert(paidBagsList.size === limit)

    val bag = paidBagsList.head

    bag.getPaymentResult.get match {
      case SuccessfulPaymentResult(_, _) => assert(true)
      case _ => assert(false)
    }
  }

  test("test payment process - simple negative") {
    val bagIterator = Seq(Bag("", Client("", FailPaymentMethod())))
    val paidBags = new BagPaymentProcessor().process(bagIterator)
    val paidBagsList = Await.result(paidBags, Duration.Inf).toList

    assert(paidBagsList.size === 1)

    val bag = paidBagsList.head

    bag.getPaymentResult.get match {
      case FailedPaymentResult(_) => assert(true)
      case _ => assert(false)
    }
  }

}
