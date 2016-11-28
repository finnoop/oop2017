package eightyDays.scala.account

import java.util.UUID

import withdrawal.Limited
import fee.LowBalancePerBooking

case class Current(override val withdrawLimit: Amount) // for Limited
                  (override val fee: Amount, override val threshold: Amount) // for LowBalancePerBooking
                  (override val owner: UUID, override val bookings: Seq[Booking] = Seq()) // for account

  extends Account(owner, bookings, Current(withdrawLimit)(fee, threshold))
    with Limited with LowBalancePerBooking
