package eightyDays.scala211.bank

import eightyDays.scala211.bank.account.{Account, Amount}

  abstract class Partner(val name: String) {
    def asset(bank: Bank): Amount = bank.filterAccounts(Account.byOwner(this)).foldLeft(BigDecimal.valueOf(0)) { case (balance, account) => balance + account.balance }
  }

  object Partner {
    def byName(name: String): Partner => Boolean = partner => partner.name == name
  }

  case class Person(firstName: String, override val name: String) extends Partner(name)
  case class LegalEntity(override val name: String) extends Partner(name)

  object Person {
    def byFirstname(firstName: String): Partner => Boolean = {
      case Person(`firstName`, _) => true
      case _ => false
    }
  }

  case class Identification(number: String = java.util.UUID.randomUUID.toString)