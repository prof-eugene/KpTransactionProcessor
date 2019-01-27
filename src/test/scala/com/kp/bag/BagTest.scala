package com.kp.bag

import com.kp.client.Client
import com.kp.delivery.PendingDeliveryOrder
import com.kp.payment.result.SuccessfulPaymentResult
import org.scalatest.FunSuite

class BagTest extends FunSuite {

  test("testPaymentResult") {

  }

  test("testClient") {
    val bag = Bag("", Client())
    assert(bag.id === "")
    assert(bag.client === Client())
  }

  test("testId") {
    val bag = Bag("a123", null)
    assert(bag.id === "a123")
    assert(bag.client === null)
  }

  test("testGetPaymentResult - default") {
    assert(Bag("", null).getPaymentResult === None)
  }


  test("testGetPaymentResult - value set") {
    assert(Bag("", null, paymentResult = Some(SuccessfulPaymentResult("A", "B"))).getPaymentResult === Some(SuccessfulPaymentResult("A", "B")))
  }

  test("testGetSumInCents") {
    val bag = Bag("", null)
    assert(bag.sumInCents === bag.getSumInCents)
  }

  test("testGetDeliveryOrder - default") {
    assert(Bag("", null).getDeliveryOrder === None)
  }


  test("testGetDeliveryOrder - value set") {
    assert(Bag("", null, deliveryOrder = Some(PendingDeliveryOrder())).getDeliveryOrder === Some(PendingDeliveryOrder()))
  }

}
