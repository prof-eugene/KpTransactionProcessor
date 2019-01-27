package com.kp.tool

import com.kp.client.Client
import org.scalatest.FunSuite

class BagGeneratorTest extends FunSuite {

  test("Test simple") {
    val bagList = new BagGenerator(1, 0).toList
    assert(bagList.size === 1)
    val bag = bagList.head
    assert(bag.client.paymentMethod === Client.defaultPaymentMethod)
    assert(bag.getPaymentResult === None)
    assert(bag.getDeliveryOrder === None)

  }

}
