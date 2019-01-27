package com.kp.client

import com.kp.payment.method.{MastercardPaymentMethod, VisaPaymentMethod}
import org.scalatest.FunSuite

class ClientTest extends FunSuite {

  test("testPaymentMethod") {
    assert(Client("", VisaPaymentMethod()).paymentMethod === VisaPaymentMethod())
    assert(Client("", MastercardPaymentMethod()).paymentMethod === MastercardPaymentMethod())
  }


  test("test default client") {
    val client = Client()
    assert(client.id === "")
    assert(client.paymentMethod === Client.defaultPaymentMethod)
  }

  
  test("testId") {
    val client = Client("ClientId", Client.defaultPaymentMethod)
    assert(client.id === "ClientId")
  }


}
