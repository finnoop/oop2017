package eightyDays.scala

;

import java.util.UUID

import eightyDays.scala.account.{Current, Saving}
import org.scalatest.WordSpec

class AccountingTest extends WordSpec {
  "A saving account" when {
    "deposit and withdraw" in {
      val testee = Saving(500)(UUID.randomUUID())
      assert(0 === testee.balance)
      assert(400 === testee.deposit(400).balance)
      assert(101 === testee.deposit(400).withdraw(299).balance)
    }
    "overdraw" in {
      val testee = Saving(500)(UUID.randomUUID())
      assert(0 === testee.balance)
      assert(400 === testee.deposit(400).balance)
      assert(100 === testee.deposit(400).withdraw(300).balance)
      assert("Withdraw not possible due to insufficient getBalance" === intercept[RuntimeException] {
        testee.deposit(400).withdraw(401)
      }.getMessage)
    }
    "over withdraw limit" in {
      val testee = Saving(200)(UUID.randomUUID())
      assert(0 === testee.balance)
      assert(100 === testee.deposit(300).withdraw(200).balance)
      assert("Value over withdraw limit" === intercept[RuntimeException] {
        testee.deposit(400).withdraw(201)
      }.getMessage)
    }
  }
  "A current account" when {
    "deposit and withdraw" in {
      val testee = Current(100)(1.5, 40)(UUID.randomUUID())
      assert(0 === testee.balance)
      assert(10.5 === testee.deposit(12).balance)
      assert(7.0 === testee.deposit(12).withdraw(2).balance)
    }
    "withdraw over limite" in {
      val testee = Current(100)(1.5, 40)(UUID.randomUUID())
      assert("Value over withdraw limit" === intercept[RuntimeException] {
        testee.withdraw(400)
      }.getMessage)
    }
  }
}
