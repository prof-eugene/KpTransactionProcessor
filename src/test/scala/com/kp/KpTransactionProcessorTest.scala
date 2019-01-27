package com.kp

import org.scalatest.FunSuite

class KpTransactionProcessorTest extends FunSuite {

  test("testRun - general end-to-end test") {
    new KpTransactionProcessor().run(100)
  }

}
