package eightyDays.scala.account.withdrawal

import java.time.LocalDateTime
import eightyDays.scala.account._

trait NoOverdraw extends Account {
  override def withdraw(value: Amount, valuta: LocalDateTime): Account =  if (balance - value < 0)
                                                                            throw new RuntimeException("Withdraw not possible due to insufficient getBalance")
                                                                          else
                                                                            super.withdraw(value, valuta)
}
