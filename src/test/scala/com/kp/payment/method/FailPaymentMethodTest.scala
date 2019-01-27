package com.kp.payment.method

import com.kp.bag.Bag
import com.kp.payment.result.FailedPaymentResult
import org.scalatest.FunSuite

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


class FailPaymentMethodTest extends FunSuite {

  test("makePayment method always fails") {
    val pm = PaymentMethod("Failed")
    assert(pm.makePayment(0) === false)
  }


  test("pay method always fails") {
    val expected = FailedPaymentResult("failed")

    val pm = PaymentMethod("Failed")
    val resultFuture = Future {
      pm.pay(Bag("", null))
    }

    val actual = Await.result(resultFuture, Duration.Inf)
    assert(actual.value.get.get === expected)
  }
}
