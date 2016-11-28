package eightyDays.scala.account

import java.util.UUID

import eightyDays.scala.account.withdrawal.{Limited, NoOverdraw}

case class Saving(override val withdrawLimit: Amount) // for Limited
                 (override val owner: UUID, override val bookings: Seq[Booking] = Seq()) // for account

  extends Account(owner, bookings, Saving(withdrawLimit))
    with Limited with NoOverdraw
