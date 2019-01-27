package com.kp.payment.method

import com.kp.common.Loggable
import com.kp.payment.Payable
import com.kp.payment.result.{FailedPaymentResult, PaymentResult, SuccessfulPaymentResult}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**+
  * General trait to support payment methods
  */
trait PaymentMethod extends Loggable {
  /**+
    * General template method of payment process
    * @param payable payable object that provides sum to pay
    * @return true if payment completed successfully, false - otherwise
    */
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

  /**+
    * Actual payment method to be implemented particular payment methods (engines)
    * @param sumInCents sum to pay in cents
    * @return true, if completed successfully
    */
  def makePayment(sumInCents: Int): Boolean
}


object PaymentMethod {
  val processingMs: Int = 50

  /**+
    * factory method to construct payment methods
    * @param name payment method name (Visa, Mastercard)
    * @return
    */
  def apply(name: String): PaymentMethod = {
    name match {
      case "Visa" => VisaPaymentMethod()
      case "Mastercard" => MastercardPaymentMethod()
      case "Failed" => FailPaymentMethod()
      case "Exception" => ExceptionPaymentMethod()
    }
  }
}