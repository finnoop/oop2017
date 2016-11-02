package eightyDays.scala211.bank

import java.time.LocalDateTime

import eightyDays.scala211.bank.account.fee.PerBooking
import eightyDays.scala211.bank.account.withdrawal.{Limited, NoWithdraw}

import scala.language.implicitConversions

package object account {
  type Amount = BigDecimal
  implicit def booking2Amount(booking: Booking): Amount = booking.value
}

package account {

  case class Booking(value: Amount, valuta: java.time.LocalDateTime)

  abstract class Account(val owner: Partner,
                         val bookings: Seq[Booking],
                         factoryMethod: ((Partner, Seq[Booking]) => Account)) {

    val number = Identification()

    def balance: Amount = bookings.foldLeft(0:Amount)((balance, booking) => balance + booking)

    def withdraw(value: Amount,
                 valuta: LocalDateTime = LocalDateTime.now()) =
        if (value > 0)
          post(-value, valuta)
        else
          throw new RuntimeException("Withdraw of negative amount not allowed")

    def deposit(value: Amount,
                 valuta: LocalDateTime = LocalDateTime.now()) =
        if (value > 0)
          post(value, valuta)
        else
          throw new RuntimeException("Deposit of negative amount not allowed")

    protected def post(value: Amount,
                     valuta: LocalDateTime = LocalDateTime.now()) =
      factoryMethod(owner, Booking(value, valuta) +: bookings)

    override def toString: String =
      s"${getClass.getSimpleName} number:${number.number} balance:$balance"
  }

  object Account {
    def byOwner(owner: Partner): Account => Boolean =
      account => account.owner == owner
  }

  package fee {
    trait LowBalancePerBooking extends Account {
      def fee: Amount
      def threshold: Amount

      override def post(value: Amount, valuta: java.time.LocalDateTime): Account = {
        (if(balance < threshold) super.post(fee) else this).post(value)
      }
    }
  }

  package withdrawal {
    trait NoOverdraw extends Account {
      override def post(value: Amount, valuta: java.time.LocalDateTime): Account =
        if (balance - value < 0)
          throw new RuntimeException("Withdraw not possible due to insufficient balance")
        else
          super.post(value, valuta)
    }

    trait Limited extends Account {
      def withdrawLimit: Amount

      override def post(value: Amount, valuta: java.time.LocalDateTime): Account =
        if (value >= -withdrawLimit)
          super.post(value, valuta)
        else
          throw new RuntimeException("Withdraw not allowed")
    }
  }

  import fee._
  import withdrawal._

  case class Current(override val withdrawLimit: Amount) // for Limited
                    (override val fee: Amount, override val threshold: Amount) // for LowBalancePerBooking
                    (override val owner: Partner, override val bookings: Seq[Booking] = Seq()) // for account

    extends Account(owner, bookings, Current(withdrawLimit)(fee, threshold))
      with Limited with LowBalancePerBooking


  case class Saving(override val withdrawLimit: Amount) // for Limited
                   (override val owner: Partner, override val bookings: Seq[Booking] = Seq()) // for account

    extends Account(owner, bookings, Saving(withdrawLimit))
      with Limited with NoOverdraw
}
