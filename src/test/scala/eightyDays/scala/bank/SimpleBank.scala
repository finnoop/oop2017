package eightyDays.scala.bank

import org.scalatest.WordSpec

class SimpleBank extends WordSpec {

  def withPartner(partner: Partner)(test: Bank => Unit) = test(Bank("Test").add(partner)._2)

  def withPartners(partners: Partner*)(test: Bank => Unit) = test(partners.foldLeft(Bank("Test bank")) {
    (bank, person) => bank.add(person)._2
  })

  "A bank" when {
    "founded" should {
      "have no partners yet" in {
        assert(Bank("Simple bank").partners.isEmpty)
      }
    }
    "added one person" should {
      "have one person" in {
        val (_, bank) = Bank("Simple bank").add(phileasFogg)
        assert(bank.partners.size == 1)
      }
      "find this person by name" in withPartner(phileasFogg) { bank =>
        assert(bank.filterPartners(_.name == "Fogg").size == 1)
      }
      "find this person by its first name" in withPartner(phileasFogg) { bank =>
        assert(bank.filterPartners {
          case Person(firstName, _) => firstName == "Phileas"
          case _ => false
        }.size == 1)

        import Partner._
        import Person._
        assert((bank filterPartners byFirstname("Phileas") size) == 1)
        assert((bank filterPartners byName("Fogg") size) == 1)
      }
    }
    "added two persons" should {
      "have two person" in withPartners(phileasFogg, jeanPassepartout) { bank =>
        assert(bank.partners.size == 2)
      }
      "find these person by name" in withPartners(phileasFogg, jeanPassepartout) { bank =>
        assert(
          bank
            .filterPartners(p => p.name == "Fogg" || p.name == "Passepartout")
            .size == 2)
      }
    }
  }
}
