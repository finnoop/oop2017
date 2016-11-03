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

  "Saving account" in {
    val saving = account.Saving(300)(phileasFogg)
    assert(0.0 === saving.balance)
    assert(400 === saving.deposit(400).balance)
    assert(101 === saving.deposit(400).withdraw(299).balance)
  }

  "Withdraw saving account over limit" in withPartner(jeanPassepartout) { (passepartout, bank) =>
    val saving = account.Saving(300)(jeanPassepartout)
    assert(0.0 === saving.balance)
    assert(400 === saving.deposit(400).balance)
    assert(200 === saving.deposit(400).withdraw(200).balance)
    assert(intercept[RuntimeException] {
      saving.deposit(400).withdraw(301)
    }.getMessage === "Withdraw not allowed")
  }

  "Current account" in {
    val current = account.Current(100)(1.5, 40)(jeanPassepartout)
    assert(0.0 === current.balance)
    assert(10.5 === current.deposit(12).balance)
  }
}

