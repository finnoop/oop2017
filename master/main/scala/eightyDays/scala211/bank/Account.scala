package eightyDays.scala.bank

import java.time.LocalDateTime

import eightyDays.scala.bank.account.fee.PerBooking
import eightyDays.scala.bank.account.withdrawal.{Limited, NoWithdraw}

import scala.language.implicitConversions

package object account {
  type Amount = BigDecimal
}

package account {

  import scala.util.Try

  object Account {
    def byOwner(owner: Partner): Account => Boolean =
      account => account.owner == owner
  }

  case class Account(val owner: Partner,
                         val bookings: Seq[Booking],
                         factoryMethod: ((Partner, Seq[Booking]) => Account)) {
    implicit def booking2Amount(booking: Booking): Amount = booking.value

    val number = Identification()

    def balance: Amount =
      bookings.foldLeft(BigDecimal.valueOf(0))((balance, booking) =>
        balance + booking)

    def withdraw(value: Amount,
                 valuta: LocalDateTime = LocalDateTime.now()) =
        if (value > 0) post(-value, valuta)
        else
          throw new RuntimeException("Withdraw of negative amount not allowed")

    def deposit(value: Amount,
                 valuta: LocalDateTime = LocalDateTime.now(),
                 text: String = "Booking") =
      Try(
        if (value > 0) post(value, valuta, text)
        else
          throw new RuntimeException("Deposit of negative amount not allowed"))

    protected def post(value: Amount,
                     valuta: LocalDateTime = LocalDateTime.now()) =
      factoryMethod(owner, Booking(value, valuta) +: bookings)

    override def toString: String =
      s"${getClass.getSimpleName} number:${number.number} balance:$balance"
  }

  case class Booking(value: Amount,
                     valuta: java.time.LocalDateTime,
                     text: String)

  case class Portfolio(accounts: Set[Account])

  abstract class Mortgage(override val owner: Partner,
                          bookings: Seq[Booking],
                          makeNew: ((Partner, Seq[Booking]) => Account))
      extends Account(owner, bookings, makeNew)

  case class FixedRateMortgage(override val owner: Partner,
                               override val bookings: Seq[Booking] = Seq())
      extends Mortgage(owner, bookings, FixedRateMortgage)
      with NoWithdraw

  case class VariableRateMortgage(override val owner: Partner,
                                  override val bookings: Seq[Booking] = Seq())
      extends Mortgage(owner, bookings, VariableRateMortgage)

  case class Current(override val owner: Partner,
                     override val bookings: Seq[Booking] = Seq())
      extends Account(owner, bookings, Current)

  case class CreditCard(override val owner: Partner,
                        override val bookings: Seq[Booking] = Seq())
      extends Account(owner, bookings, CreditCard)

  case class Saving(override val limit: Amount)(
      override val owner: Partner,
      override val bookings: Seq[Booking] = Seq())
      extends Account(owner, bookings, Saving(limit))
      with Limited {
    override def timeframeInMonths: Int = 6
  }

  case class SavingWithFee(override val limit: Amount,
                           override val fee: Amount)(
      override val owner: Partner,
      override val bookings: Seq[Booking] = Seq())
      extends Account(owner, bookings, SavingWithFee(limit, fee))
      with Limited
      with PerBooking {
    override def timeframeInMonths: Int = 6
  }

  package fee {

    trait BalanceBased extends Account with PerBooking {
      def feeFreeBalance: Amount

      override def post(value: Amount,
                        valuta: java.time.LocalDateTime,
                        text: String): Account = {
        if (balance > feeFreeBalance)
          super.post(value - fee, text = s"${text}, Including fee on balance")
        else super.post(value)
      }
    }

    trait PerBooking extends Account {
      def fee: Amount

      override def post(value: Amount,
                        valuta: java.time.LocalDateTime,
                        text: String): Account = {
        if (value < 0)
          super.post(value - fee, text = s"${text}, Including fee on withdrawal")
        else super.post(value)
      }
    }

  }

  package overdrawn {

    trait NoOverdraw

    trait Limited {
      val limit: Amount
    }

  }

  package withdrawal {

    trait Limited extends Account {
      def limit: Amount

      def timeframeInMonths: Int

      override def post(value: Amount,
                        valuta: java.time.LocalDateTime,
                        text: String): Account =
        if (value >= -limit) super.post(value, valuta)
        else
          throw new RuntimeException("Withdraw within timeframe not allowed")

    }

    trait NoWithdraw extends Account {
      override def post(value: Amount,
                        valuta: java.time.LocalDateTime,
                        text: String): Account =
        if (value > 0.0) super.post(value, valuta)
        else throw new RuntimeException("Withdraw not allowed")
    }

  }
}
