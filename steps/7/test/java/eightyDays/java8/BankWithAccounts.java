package eightyDays.java8;

import eightyDays.java8.account.Account;
import eightyDays.java8.account.Current;
import eightyDays.java8.account.Saving;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BankWithAccounts {

    @Test
    public void testDepositAndWithdraw() {
        Bank testee = new Bank("TestBank");
        Identification phileas = testee.addPartner(new Person("Phileas", "Fogg"));

        Account current = testee.openAccount(phileas, owner -> new Current(BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40), owner));

        assertEquals(BigDecimal.valueOf(10.5), testee.deposit(current.getNumber(), BigDecimal.valueOf(12)).getBalance());
        assertEquals(BigDecimal.valueOf(10.5), testee.getAccount(current.getNumber()).get().getBalance());
        assertEquals(BigDecimal.valueOf(8.0), testee.withdraw(current.getNumber(), BigDecimal.valueOf(1)).getBalance());
    }

    @Test
    public void testMultipleAccounts() {
        Bank testee = new Bank("TestBank");
        Identification phileas = testee.addPartner(new Person("Phileas", "Fogg"));
        Account current = testee.openAccount(phileas, owner -> new Current(BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40), owner));
        Account saving = testee.openAccount(phileas, owner -> new Saving(BigDecimal.valueOf(100), owner));

        testee.deposit(current.number, BigDecimal.valueOf(42));
        testee.deposit(saving.number, BigDecimal.valueOf(8));

        assertEquals(BigDecimal.valueOf(50), testee.getAssets(phileas));
    }
}
