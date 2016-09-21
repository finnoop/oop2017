package eightyDays.scala211.bank

import org.scalatest.WordSpec

class SimpleBank extends WordSpec {
  "A bank" when {
    "founded" should {
      "has no partners yet" in {
        assert(Bank("Simple bank").partners.size == 0)
      }
    }
  }
}
