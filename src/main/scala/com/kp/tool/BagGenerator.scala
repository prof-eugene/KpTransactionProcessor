package com.kp.tool

import com.kp.bag.Bag
import com.kp.client.Client
import com.kp.common.Loggable
import com.kp.payment.method.PaymentMethod

/**+
  * Tool class to generate a list of bags
  * @param limit maximum elements to be generated
  * @param mastercardRatio ratio for Mastercard cards, otherwise will be Visa
  */
class BagGenerator(limit: Int, mastercardRatio: Double = 0.5) extends Iterator[Bag] with Loggable {
  val random = new scala.util.Random()
  var current = 0


  override def hasNext: Boolean = {
    current < limit
  }

  override def next(): Bag = {
    current += 1
    val chance = random.nextInt(100).toDouble/100
    if (chance < mastercardRatio.toDouble) {
      log.debug("New bag generated - Mastercard")
      Bag(s"B$current", Client(s"C$current", PaymentMethod("Mastercard")))
    } else {
      log.debug("New bag generated - Visa")
      Bag(s"B$current", Client(s"C$current", PaymentMethod("Visa")))
    }
  }
}
