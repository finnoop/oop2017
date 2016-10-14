package eightyDays.scala211.bank

import eightyDays.scala211.bank.partner.{Identification, Partner}
import org.scalatest.WordSpec

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
      val (mortgage, _) = bank.add(fogg, account.FixedRateMortgage(_))
      val mortgageWithOneBooking = mortgage.post(10.5)
      assert(0.0 === mortgage.balance)
      assert(10.5 === mortgageWithOneBooking.balance)
      assert(13.5 === mortgageWithOneBooking.post(3).balance)
    }
    "add one Saving account for Fogg" in withPartner(phileasFogg) { (fogg, bank) =>
      val (saving, _) = bank.add(fogg, account.Saving(300)(_))
      assert(0.0 === saving.balance)
      assert(400 === saving.post(400).balance)
      assert(101 === saving.post(400).post(-299).balance)
    }
    "add one Saving account with Fee for Passepartout" in withPartner(jeanPassepartout) { (passepartout, bank) =>
      val (saving, _) = bank.add(passepartout, account.SavingWithFee(10, 1)(_))
      assert(0.0 === saving.balance)
      assert(50 === saving.post(50).balance)
      assert(40 === saving.post(50).post(-9).balance)
    }
    "overdraw a saving account throws an exception" in withPartner(jeanPassepartout) { (passepartout, bank) =>
      val (saving, _) = bank.add(passepartout, account.SavingWithFee(10, 1)(_))
      val exception = intercept[RuntimeException] {
        saving.post(50).post(-11)
      }
      assert(exception.getMessage === "Withdraw within timeframe not allowed")
    }
    "Fogg" when {
      "has two accounts" in withPartner(phileasFogg) { (fogg, bank) =>
        val (emptySaving, bankWithAccount) = bank.add(fogg, account.Saving(0)(_))
        val (emptyCurrent, bankWithAccounts) = bankWithAccount.add(fogg, account.Current(_))

        val (current,  bankWithAssets) = bankWithAccounts.post(emptySaving, 300)._2.post(emptyCurrent, 500)

        assert(500 == current.balance)
        assert(800 == phileasFogg.asset(bankWithAssets))
      }
      "has two accounts with multiple bookings" in withPartner(phileasFogg) { (fogg, bank) =>
        val (emptySaving, bankWithAccount) = bank.add(fogg, account.Saving(0)(_))
        val (emptyCurrent, bankWithAccounts) = bankWithAccount.add(fogg, account.Current(_))

        val (List(saving, current), bankWithAssets) = bankWithAccounts.posts((emptySaving, 320), (emptyCurrent, 570))

        assert(320 == saving.balance)
        assert(570 == current.balance)
        assert(890 == phileasFogg.asset(bankWithAssets))
      }
    }
  }
}

