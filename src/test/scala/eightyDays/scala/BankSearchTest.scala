package eightyDays.scala

import org.scalatest.WordSpec

class BankSearchTest extends WordSpec {
  "A bank with customers" when {
    "searching for" in {
      val testee = Bank("Test bank")
      val fogg = Person("Fogg", "Phileas")
      val passepartout = Person("Passepartout", "Jean")
      val finnovaLeagEntity = LegalEntity("Finnova", "AG")
      val netsLeagEntity = LegalEntity("Nets", "GmbH")

      val phileas = testee.addPartner(fogg)
      val jean = testee.addPartner(passepartout)
      val finnova = testee.addPartner(finnovaLeagEntity)
      val nets = testee.addPartner(netsLeagEntity)

      assert(testee.searchPerson("Jean").nonEmpty)
      assert(testee.searchPerson("Jean").size === 1)
      assert(testee.searchPerson("Jean").head === jean)

      assert(testee.searchPartner("Passepartout").nonEmpty)
      assert(testee.searchPartner("Passepartout").size === 1)
      assert(testee.searchPartner("Passepartout").head === jean)

      assert(testee.searchPerson("Finnova").isEmpty)

      assert(testee.searchPartner("Finnova").nonEmpty)
      assert(testee.searchPartner("Finnova").size === 1)
      assert(testee.searchPartner("Finnova").head === finnova)
    }
  }
}
