package com.kp.delivery

/**+
  * General trait to support Deliverable ability
  */
trait Deliverable {
  def getDeliveryOrder: Option[DeliveryOrder]
}
