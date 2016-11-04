package eightyDays.scala.account

import eightyDays.scala.{Identification, Partner}
import scala.language.implicitConversions

abstract class Account(val owner: Partner,
                       val bookings: Seq[Booking],
                       factoryMethod: ((Partner, Seq[Booking]) => Account)) {

  val number = Identification()

  def balance: Amount = bookings.foldLeft(0: Amount)((balance, booking) => balance + booking)

  import java.time.LocalDateTime

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