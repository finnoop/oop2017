package eightyDays.scala211.bank

import eightyDays.scala211.bank.partner.{Partner, Person}
import org.scalatest.WordSpec

class SimpleBank extends WordSpec {

  def withPartner(partner: Partner)(test: Bank => Unit) = test(Bank("Test").addPartner(partner)._2)

  def withPartners(partners: Partner*)(test: Bank => Unit) = test(partners.foldLeft(Bank("Test bank")) {
    (bank, person) => bank.addPartner(person)._2
  })

  "A bank" when {
    "founded" should {
      "have no partners yet" in {
        assert(Bank("Simple bank").partners.isEmpty)
      }
    }
    "added one person" should {
      "have one person" in {
        val (_, bank) = Bank("Simple bank").addPartner(Person("Phileas", "Fogg"))
        assert(bank.partners.size == 1)
      }
      "find this person by name" in withPartner(Person("Phileas", "Fogg")) { bank =>
        assert(bank.filter(_.name == "Fogg").size == 1)
      }
      "find this person by its first name" in withPartner(Person("Phileas", "Fogg")) { bank =>
        assert(bank.filter {
          case Person(firstName, _) => firstName == "Phileas"
          case _ => false
        }.size == 1)

        import Person._
        import Partner._
        assert((bank filter byFirstname("Phileas") size) == 1)
        assert((bank filter byName("Fogg") size) == 1)
      }
    }
    "added two persons" should {
      "have two person" in withPartners(Person("Phileas", "Fogg"), Person("Jean", "Passepartout")) { bank =>
        assert(bank.partners.size == 2)
      }
      "find these person by name" in withPartners(Person("Phileas", "Fogg"), Person("Jean", "Passepartout")) { bank =>
        assert(
          bank
            .filter(p => p.name == "Fogg" || p.name == "Passepartout")
            .size == 2)
      }
    }
  }
}
