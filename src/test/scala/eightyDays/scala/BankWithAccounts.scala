package eightyDays.scala

import org.scalatest.WordSpec

class BankWithAccounts extends WordSpec {
  "A bank" when {
    "a customer with an account" should {
      "can deposit and withdraw money" in {
        val testee = Bank("Test bank")
        val phileas = testee.addPartner(Person("Phileas", "Fogg"))
        val current = testee.openAccount(phileas, account.Current(100)(1.5, 40))
        assert(10.5 === testee.deposit(current.number, 12).balance)
        assert(10.5 === testee.account(current.number).get.balance)
        assert(8.0 === testee.withdraw(current.number, 1).balance)
      }
    }
  }
  "A customer" when {
    "has multiple account from different banks" in {
      val testee = Bank("Test bank")
      val phileas = testee.addPartner(Person("Phileas", "Fogg"))
      val current = testee.openAccount(phileas, account.Current(100)(1.5, 40))
      val saving = testee.openAccount(phileas, account.Saving(100))

      testee.deposit(current.number, 42)
      testee.deposit(saving.number, 8)

      assert(50.0 === testee.assets(phileas))
    }
  }
}
