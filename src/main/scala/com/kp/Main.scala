package com.kp

import com.kp.common.Loggable
import com.kp.payment.method.PaymentMethod
import com.kp.process.{BagDeliveryProcessor, BagPaymentProcessor}
import com.kp.tool.BagGenerator

import scala.concurrent.Await
import scala.concurrent.duration.Duration


object Main extends App with Loggable {

  new KpTransactionProcessor().run(1000)
}

