package com.kp.common

import org.slf4j.{Logger, LoggerFactory}

/**+
  * General trait to provide Logger object
  */
trait Loggable {
  val log: Logger = LoggerFactory.getLogger(this.getClass)
}
