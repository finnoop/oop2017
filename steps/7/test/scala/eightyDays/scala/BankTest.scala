package eightyDays.scala

import java.util.UUID

import org.scalatest.WordSpec

class BankTest extends WordSpec {
  val have = afterWord("have")

  "A new bank" must have {
    "a given name" in {
      val testee = new Bank("Test bank")
      assert(testee.name === "Test bank")
    }
    "now partners" in {
      val testee = new Bank("Test bank")
      assert(None === testee.person(UUID.randomUUID()))
    }
    "one customer after adding one" in {
      val testee = new Bank("Test bank")
      val fogg = Person("Phileas", "Fogg")

      val phileas = testee.addPartner(fogg)

      assert(Some(fogg) === testee.person(phileas))
      assert(phileas === testee.addPartner(fogg))
    }
  }
}
