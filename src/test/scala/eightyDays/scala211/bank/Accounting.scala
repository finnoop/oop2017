package eightyDays.scala211.bank

import eightyDays.scala211.bank.partner.{Identification, Partner, Person}
import org.scalatest.WordSpec

class Accounting extends WordSpec {

  def withPartner(partner: Partner)(test: (Identification, Bank) => Unit) = {
    val (id, bank) = Bank("Test").addPartner(partner)
    test(id, bank)
  }


  def withPartners(partners: Partner*)(test: Bank => Unit) = test(partners.foldLeft(Bank("Test bank")) {
    (bank, person) => bank.addPartner(person)._2
  })

  "A bank" when {
    "add one FixedRateMortgage account for Fogg" in withPartner(phileasFogg) { (idFogg, bank) =>
      val (mortgage, _) = bank.addAccount(idFogg, account.FixedRateMortgage())
      val mortgageWithOneBooking = mortgage.post(10.5)
      println(mortgageWithOneBooking)
      println(mortgageWithOneBooking.post(3).balance)
    }
    "add one Saving account for Fogg" in withPartner(phileasFogg) { (idFogg, bank) =>
      val (mortgage, _) = bank.addAccount(idFogg, account.Saving(300)())
      val mortgageWithOneBooking = mortgage.post(400)
      println(mortgageWithOneBooking)
      println(mortgageWithOneBooking.post(-299).balance)
    }
    "overdraw a saving account throws an exception" in withPartner(jeanPassepartout) { (idPasspartout, bank) =>
      val (mortgage, _) = bank.addAccount(idPasspartout, account.Saving(10)())
      val mortgageWithOneBooking = mortgage.post(50)

      val thrown = intercept[RuntimeException]{mortgageWithOneBooking.post(-12)}
      assert(thrown.getMessage === "Withdraw within timeframe not allowed")
    }
  }
}
