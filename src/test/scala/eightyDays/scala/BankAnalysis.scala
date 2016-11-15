package eightyDays.scala

import org.scalatest.WordSpec

class BankAnalysis extends WordSpec {
  "A bank" when {
    "with lots of customers" should {
      "perform ABC analysis" in {
        val testee = new Bank("A good bank")
        val a = testee.addPartner(Person("pA","pa"))
        val b = testee.addPartner(Person("pB","pb"))
        val c = testee.addPartner(Person("pC","pc"))
        val d = testee.addPartner(Person("pD","pd"))

        testee.deposit(testee.openAccount(a, account.Current(100)(1.5, 40)).number, 1100)
        testee.deposit(testee.openAccount(b, account.Current(100)(1.5, 40)).number, 600)
        testee.deposit(testee.openAccount(c, account.Current(100)(1.5, 40)).number, 200)

        println(testee)

        println(testee.abc)
      }
    }
  }
}
