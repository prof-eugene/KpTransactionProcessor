package com.kp.payment.method

import com.kp.common.Loggable
import com.kp.payment.Payable
import com.kp.payment.result.{FailedPaymentResult, PaymentResult, SuccessfulPaymentResult}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


trait PaymentMethod extends Loggable {

  def pay(payable: Payable): Future[PaymentResult] = {
    Future {
      log.info("Trying to pay amount: {}", payable.getSumInCents)
      try {
        if (makePayment(payable.getSumInCents)) {
          log.info("Payment - completed")
          SuccessfulPaymentResult(s"RRN:000123", s"CODE:456000")
        } else {
          log.warn("Payment - failed")
          FailedPaymentResult("failed")
        }
      } catch {
        case e: Exception => log.error(e.getMessage)
          FailedPaymentResult(e.getMessage)
      }
    }
  }

  def makePayment(sumInCents: Int): Boolean
}


object PaymentMethod {
  val processingMs: Int = 50

  def apply(name: String): PaymentMethod = {
    name match {
      case "Visa" => VisaPaymentMethod()
      case "Mastercard" => MastercardPaymentMethod()
      //   _ => throw new IllegalArgumentException(/*s"Payment method $_ is unknown!"*/)
    }
  }
}