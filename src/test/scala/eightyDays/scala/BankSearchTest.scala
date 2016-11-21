package eightyDays.scala

import org.scalatest.WordSpec

class BankSearchTest extends WordSpec {
  "A bank with customers" when {
    "searching for" in {
      val testee = Bank("Test bank")
      val fogg = Person("Fogg", "Phileas")
      val passepartout = Person("Passepartout", "Jean")
      val finnova = LegalEntity("Finnova", "AG")

      val phileas = testee.addPartner(fogg)
      val jean = testee.addPartner(passepartout)
      val fin = testee.addPartner(finnova)

      assert(testee.searchPerson("Jean").nonEmpty)
      assert(testee.searchPartner("Passepartout").nonEmpty)

      assert(testee.searchPerson("Finnova").isEmpty)
      assert(testee.searchPartner("Finnova").nonEmpty)
    }
  }
}
