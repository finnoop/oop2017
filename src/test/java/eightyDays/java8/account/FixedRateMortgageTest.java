package eightyDays.java8.account;


import eightyDays.java8.bank.partner.Partner;
import eightyDays.java8.bank.partner.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.fail;

public class FixedRateMortgageTest {

    private Partner phileasFogg;
    private Account foggAccount;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        phileasFogg = new Person("Phileas","Fogg");
        foggAccount = new FixedRateMortgage(phileasFogg);
    }

    @Test
    public void withdraw() {
        foggAccount.deposit(new BigDecimal("1000"));

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Withdraw not allowed");
        foggAccount.withdraw(new BigDecimal("50"));

        fail("Should not be reached!");
    }
}
