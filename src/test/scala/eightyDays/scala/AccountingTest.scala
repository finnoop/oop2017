package eightyDays.scala

import org.scalatest.WordSpec

import scala.util.{Failure, Success}

class AccountingTest extends WordSpec {

  "Saving account" when {
    "normal" in {
      val saving = account.Saving(300)(phileasFogg)
      assert(0.0 === saving.balance)
      assert(400 === saving.deposit(400).balance)
      assert(101 === saving.deposit(400).withdraw(299).balance)
    }

    "Withdraw over limit" in {
      val saving = account.Saving(300)(jeanPassepartout)
      assert(0.0 === saving.balance)
      assert(400 === saving.deposit(400).balance)
      assert(100 === saving.deposit(400).withdraw(300).balance)
      assert(intercept[RuntimeException] {
        saving.deposit(400).withdraw(301)
      }.getMessage === "Withdraw not allowed")
    }
  }
  "Current account withdraw over limit" in {
    val current = account.Current(100)(1.5, 40)(jeanPassepartout)
    assert(0.0 === current.balance)
    assert(10.5 === current.deposit(12).balance)
    assert(7.0 === current.deposit(12).withdraw(2).balance)
    assert(intercept[RuntimeException] {
      current.withdraw(400)
    }.getMessage === "Withdraw not allowed")
  }
}

