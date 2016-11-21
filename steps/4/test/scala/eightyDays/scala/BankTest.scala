package eightyDays.scala

import org.scalatest.WordSpec

class BankTest extends WordSpec {
  val have = afterWord("have")

  "A new bank" must have {
    "a given name" in {
      val testee = Bank("Test bank")
      assert(testee.name === "Test bank")
    }
    "now partners" in {
      val testee = Bank("Test bank")
      assert(None === testee.partner(Identification()))
    }
    "one customer after adding one" in {
      val testee = Bank("Test bank")
      val fogg = Person("Phileas", "Fogg")

      val phileas = testee.addPartner(fogg)

      assert(Some(fogg) === testee.partner(phileas))
      assert(phileas === testee.addPartner(fogg))
    }
  }
}
