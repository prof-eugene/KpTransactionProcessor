package com.kp.payment.method

import com.kp.bag.Bag
import com.kp.payment.result.FailedPaymentResult
import org.scalatest.FunSuite

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class ExceptionPaymentMethodTest extends FunSuite {

  test("makePayment method always throws an exception") {
    val pm = ExceptionPaymentMethod()
    intercept[Exception] {
      pm.makePayment(0)
    }
  }


  test("pay method always fails after exception") {
    val expected = FailedPaymentResult("PaymentMethod exception")

    val pm = ExceptionPaymentMethod()
    val resultFuture = Future {
      pm.pay(Bag("", null))
    }

    Thread.sleep(100)
    val actual = Await.result(resultFuture, Duration.Inf)
    assert(actual.value.get.get === expected)
  }

}
