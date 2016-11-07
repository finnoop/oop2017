package eightyDays.scala.account

import eightyDays.scala.Partner
import eightyDays.scala.account.withdrawal.{Limited, NoOverdraw}

case class Saving(override val withdrawLimit: Amount) // for Limited
                 (override val owner: Partner, override val bookings: Seq[Booking] = Seq()) // for account

  extends Account(owner, bookings, Saving(withdrawLimit))
    with Limited with NoOverdraw
