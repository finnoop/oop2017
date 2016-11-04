package eightyDays.scala.account

import eightyDays.scala.Partner
import fee._
import withdrawal._

case class Current(override val withdrawLimit: Amount) // for Limited
                  (override val fee: Amount, override val threshold: Amount) // for LowBalancePerBooking
                  (override val owner: Partner, override val bookings: Seq[Booking] = Seq()) // for account

  extends Account(owner, bookings, Current(withdrawLimit)(fee, threshold))
    with Limited with LowBalancePerBooking
