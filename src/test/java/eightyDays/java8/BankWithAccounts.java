package eightyDays.java8;

import eightyDays.java8.account.Account;
import eightyDays.java8.account.Current;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class BankWithAccounts {

    @Test
    public void testDepositAndWithdraw() {
        Bank testee = new Bank("TestBank");
        Identification phileas = testee.addPartner(new Person("Phileas", "Fogg"));

        Identification current = testee.openAccount(phileas, (owner) -> new Current(BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40), owner));

    }
    /*
                assert(10.5 === testee.deposit(current.number, 12).balance)
                assert(10.5 === testee.account(current.number).get.balance)
                assert(8.0 === testee.withdraw(current.number, 1).balance)
*/
}
