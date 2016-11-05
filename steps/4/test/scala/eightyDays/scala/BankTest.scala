package eightyDays.scala

import org.scalatest.WordSpec

class BankTest extends WordSpec {
  "A new bank" must {
    "has a given name" in {
      val out = Bank("Test bank")
      assert(out.name === "Test bank")
    }
    "has now partners" in {
      val out = Bank("Test bank")
      assert(None === out.partner(Identification()))
    }
    "has one customer after adding one" in {
      val out = Bank("Test bank")
      val fogg = Person("Phileas", "Fogg")

      val phileas = out.addPartner(fogg)

      assert(Some(fogg) === out.partner(phileas))
      assert(phileas === out.addPartner(fogg))
    }
  }
}
