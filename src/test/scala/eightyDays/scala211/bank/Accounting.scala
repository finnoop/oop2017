package eightyDays.scala211.bank

import org.scalatest.WordSpec

import scala.util.{Failure, Success}

class Accounting extends WordSpec {

  def withPartner(partner: Partner)(test: (Identification, Bank) => Unit) = {
    val (id, bank) = Bank("Test").add(partner)
    test(id, bank)
  }


  def withPartners(partners: Partner*)(test: Bank => Unit) = test(partners.foldLeft(Bank("Test bank")) {
    (bank, person) => bank.add(person)._2
  })

  "A bank" when {
    "add one FixedRateMortgage account for Fogg" in withPartner(phileasFogg) { (fogg, bank) =>
      val mortgage = bank.add(fogg, account.FixedRateMortgage(_))
      val mortgageWithOneBooking = mortgage.deposit(10.5).get
      assert(0.0 === mortgage.balance)
      assert(10.5 === mortgageWithOneBooking.balance)
      assert(13.5 === mortgageWithOneBooking.deposit(3).get.balance)
    }
    "add one Saving account for Fogg" in withPartner(phileasFogg) { (fogg, bank) =>
      val saving = bank.add(fogg, account.Saving(300)(_))
      assert(0.0 === saving.balance)
      assert(400 === saving.deposit(400).get.balance)
      assert(101 === saving.deposit(400).flatMap(_.withdraw(299)).get.balance)
    }

    "add one Saving account with Fee for Passepartout" in withPartner(jeanPassepartout) { (passepartout, bank) =>
      val saving = bank.add(passepartout, account.SavingWithFee(10, 1)(_))
      assert(0.0 === saving.balance)
      assert(50 === saving.deposit(50).get.balance)
      assert(40 === saving.deposit(50).flatMap(_.withdraw(9)).get.balance)
    }

    "overdraw a saving account throws an exception" in withPartner(jeanPassepartout) { (passepartout, bank) =>
      val saving = bank.add(passepartout, account.SavingWithFee(10, 1)(_))
      saving.deposit(50).flatMap(_.withdraw(11)) match {
        case Failure(exception) => assert(exception.getMessage === "Withdraw within timeframe not allowed")
        case Success(_) => fail("Should fail")
      }
    }
    "Fogg" when {
      "has two accounts" in withPartner(phileasFogg) { (fogg, bank) =>
        val emptySaving = bank.add(fogg, account.Saving(0)(_))
        val emptyCurrent = bank.add(fogg, account.Current(_))

        val saving = bank.deposit(emptySaving, 300)
        val current = bank.deposit(emptyCurrent, 500)

        assert(300 == saving.get.balance)
        assert(500 == current.get.balance)
        assert(800 == phileasFogg.asset(bank))
      }
    }
  }
}

