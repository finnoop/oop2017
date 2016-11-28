package eightyDays.scala.account

import java.util.UUID

import scala.language.implicitConversions

abstract class Account(val owner: UUID,
                       val bookings: Seq[Booking],
                       factoryMethod: ((UUID, Seq[Booking]) => Account)) {

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
}