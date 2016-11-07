package eightyDays.scala.account.fee

import java.time.LocalDateTime
import eightyDays.scala.account.{Account, Amount}

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
