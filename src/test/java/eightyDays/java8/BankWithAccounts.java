package eightyDays.java8;

import eightyDays.java8.account.Account;
import eightyDays.java8.account.Current;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BankWithAccounts {

    @Test
    public void testDepositAndWithdraw() {
        Bank testee = new Bank("TestBank");
        Identification phileas = testee.addPartner(new Person("Phileas", "Fogg"));

        Account current = testee.openAccount(phileas, (owner) -> new Current(BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40), owner));

        assertEquals(BigDecimal.valueOf(10.5), testee.deposit(current.getNumber(), BigDecimal.valueOf(12)).getBalance());
        assertEquals(BigDecimal.valueOf(10.5), testee.getAccount(current.getNumber()).get().getBalance());
        assertEquals(BigDecimal.valueOf(8.0), testee.withdraw(current.getNumber(), BigDecimal.valueOf(1)).getBalance());
    }

    /*

      "A customer" when {
    "has multiple account from different banks" in {
      implicit val testee = Bank("Test bank")
      val phileas = testee.addPartner(Person("Phileas", "Fogg"))
      val current = testee.openAccount(phileas, account.Current(100)(1.5, 40))
      val saving = testee.openAccount(phileas, account.Saving(100))

      testee.deposit(current.number, 42)
      testee.deposit(saving.number, 8)

      assert(50.0 === testee.assets(phileas))
    }
  }

     */
}
