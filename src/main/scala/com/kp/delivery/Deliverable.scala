package com.kp.delivery

trait Deliverable {
  def getDeliveryOrder: Option[DeliveryOrder]
}
