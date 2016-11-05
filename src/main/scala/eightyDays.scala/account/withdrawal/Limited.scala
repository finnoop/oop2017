package eightyDays.scala.account.withdrawal

import java.time.LocalDateTime
import eightyDays.scala.account.{Account, Amount}

trait Limited extends Account {
  val withdrawLimit: Amount

  override def withdraw(value: Amount, valuta: LocalDateTime): Account =  if (value <= withdrawLimit)
                                                                            super.withdraw(value, valuta)
                                                                          else
                                                                            throw new RuntimeException("Withdraw not allowed")
}
