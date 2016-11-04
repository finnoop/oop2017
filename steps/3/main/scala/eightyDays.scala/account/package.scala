package eightyDays.scala

package object account {
  type Amount = BigDecimal

  implicit def booking2Amount(booking: Booking): Amount = booking.value
}
