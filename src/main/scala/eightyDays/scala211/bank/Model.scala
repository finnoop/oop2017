package eightyDays.scala211.bank

import eightyDays.scala211.bank.account.{Account, FixedRateMortgage}
import eightyDays.scala211.bank.partner.{Identification, Partner, Person}

package partner {

  import eightyDays.scala211.bank.account.FixedRateMortgage

  case class Identification(number: String = java.util.UUID.randomUUID.toString)

  abstract class Partner(val name: String)

  object Partner {
    def byName(name: String): Partner => Boolean = partner => partner.name == name
  }

  case class Person(firstName: String, override val name: String) extends Partner(name)

  object Person {
    def byFirstname(firstName: String): Partner => Boolean = {
      //case person: Person => person.firstName == firstName
      // alternative
      case Person(`firstName`, _) => true
      case _ => false
    }
  }

}


package account {

  import scala.{BigDecimal => Amount}
  import java._

  import eightyDays.scala211.bank.account.withdrawal.{Limited, NoWithdraw}

  case class Booking(value: Amount, valuta: time.LocalDateTime)

  case class Portfolio(accounts: Set[Account])

  abstract class Account(val bookings: Seq[Booking], makeNew: (Seq[Booking] => Account)) {
    implicit def booking2Amount(booking: Booking): Amount = booking.value

    def balance: Amount = bookings.foldLeft(BigDecimal.valueOf(0))((balance, booking) => balance + booking)

    def post(value: Amount, valuta: time.LocalDateTime = time.LocalDateTime.now()) = makeNew(Booking(value, valuta) +: bookings)
  }


  abstract class Mortgage(bookings: Seq[Booking], makeNew: (Seq[Booking] => Account)) extends Account(bookings, makeNew)

  case class FixedRateMortgage(override val bookings: Seq[Booking] = Seq()) extends Mortgage(bookings, FixedRateMortgage) with NoWithdraw

  case class VariableRateMortgage(override val bookings: Seq[Booking] = Seq()) extends Mortgage(bookings, VariableRateMortgage)

  case class Current(override val bookings: Seq[Booking] = Seq()) extends Account(bookings, Current)

  case class CreditCard(override val bookings: Seq[Booking] = Seq()) extends Account(bookings, CreditCard)

  case class Saving(override val limit: Amount)(override val bookings: Seq[Booking] = Seq()) extends Account(bookings, Saving(limit)) with Limited {
    override def timeframeInMonths: Int = 6
  }

  package fee {

    trait Fixed

    trait BalanceBased

    trait WithoutAny

    trait PerBooking

  }

  package overdrawnLimits {

    trait NoOverdraw

    trait Limited {
      val limit: Amount
    }

    trait Unlimitted

  }

  package withdrawal {
    trait NoLimit

    trait Limited extends Account {
      def limit: Amount
      def timeframeInMonths: Int
      override def post(value: Amount, valuta: java.time.LocalDateTime): Account = {
        println(value,limit)
        if (value >= -limit) super.post(value, valuta) else throw new RuntimeException("Withdraw within timeframe not allowed")
      }
    }

    trait NoWithdraw extends Account {
      override def post(value: Amount, valuta: java.time.LocalDateTime): Account = {
        if (value > 0.0) super.post(value, valuta) else throw new RuntimeException("Withdraw not allowed")
      }
    }
  }

}

case class Bank(name: String, partners: Map[Identification, Partner] = Map[Identification, Partner](), accounts: Set[Account] = Set[Account]()) {
  def addAccount(idFogg: Identification, account: Account): (Account, Bank) = (account, copy(accounts = accounts + account))

  // unnoetig, da partner schon einen default value hat
  def this(name: String) = this(name, Map())

  def find(predicate: Partner => Boolean): Option[Partner] = partners
    .find(p => predicate(p._2))
    .map(_._2)

  def filter(predicate: Partner => Boolean): Set[Partner] =
    partners
      .filter(p => predicate(p._2))
      .values
      .toSet

  def addPartner(pPartner: Partner): (Identification, Bank) =
    partners
      .find(_._2 == pPartner)
      .map { entry => (entry._1, this) }
      .getOrElse {
        val result = Identification()
        (result, copy(partners = partners + (result -> pPartner)))
      }
}