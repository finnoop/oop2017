package eightyDays.scala

import java.time.LocalDateTime
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

    def balance: Amount = bookings.foldLeft(0: Amount)((balance, booking) => balance + booking)

    def withdraw(value: Amount, valuta: LocalDateTime = LocalDateTime.now()) =
      if (value > 0)
        post(-value, valuta)
      else
        throw new RuntimeException("Withdraw of negative amount not allowed")

    def deposit(value: Amount, valuta: LocalDateTime = LocalDateTime.now()) =
      if (value > 0)
        post(value, valuta)
      else
        throw new RuntimeException("Deposit of negative amount not allowed")

    protected[account] def post(value: Amount, valuta: LocalDateTime) = factoryMethod(owner, Booking(value, valuta) +: bookings)

    override def toString: String =
      s"${getClass.getSimpleName} number:${number.number} getBalance:$balance"
  }

  package fee {

    trait LowBalancePerBooking extends Account {
      val fee: Amount

      val threshold: Amount

      private[this] def feeIfPoossible(account: Account, valuta: LocalDateTime) =
        if (account.balance < threshold)
          account.post(-fee, valuta)
        else
          account

      override def withdraw(value: Amount, valuta: LocalDateTime): Account = feeIfPoossible(super.withdraw(value, valuta), valuta)

      override def deposit(value: Amount, valuta: LocalDateTime): Account = feeIfPoossible(super.deposit(value, valuta), valuta)
    }
  }

  package withdrawal {

    trait NoOverdraw extends Account {

      override def withdraw(value: Amount, valuta: LocalDateTime): Account =
        if (balance - value < 0)
          throw new RuntimeException("Withdraw not possible due to insufficient getBalance")
        else
          super.withdraw(value, valuta)
    }

    trait Limited extends Account {
      val withdrawLimit: Amount

      override def withdraw(value: Amount, valuta: LocalDateTime): Account =
        if (value <= withdrawLimit)
          super.withdraw(value, valuta)
        else
          throw new RuntimeException("Withdraw not allowed")
    }
  }
}