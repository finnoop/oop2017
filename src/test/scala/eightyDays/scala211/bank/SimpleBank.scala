package eightyDays.scala211.bank

import eightyDays.scala211.bank.partner.Person
import org.scalatest.WordSpec

class SimpleBank extends WordSpec {
  "A bank" when {
    "founded" should {
      "have no partners yet" in {
        assert(Bank("Simple bank").partners.isEmpty)
      }
    }
    "added one person" should {
      val (_, bank) = Bank("Simple bank").addPartner(Person("Phileas", "Fogg"))
      "have one person" in {
        assert(bank.partners.size == 1)
      }
      "find this person by name" in {
        assert(bank.find(_.name == "Fogg").size == 1)
      }
      "find this person by its first name" in {
        assert(bank.find {
          case Person(firstName, _) => firstName == "Phileas"
          case _ => false
        }.size == 1)
      }
    }
    "added two persons" should {
      val (_, bank) = Bank("Simple bank")
        .addPartner(Person("Phileas", "Fogg"))
        ._2
        .addPartner(Person("Jean", "Passepartout"))
      "have two person" in {
        assert(bank.partners.size == 2)
      }
      "find these person by name" in {
        assert(
          bank
            .find(p => p.name == "Fogg" || p.name == "Passepartout")
            .size == 2)
      }
    }
  }
}
