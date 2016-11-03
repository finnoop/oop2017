package eightyDays.java8.account;


import eightyDays.java8.bank.partner.Partner;
import eightyDays.java8.bank.partner.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SavingAccountTest {

    private Partner phileasFogg;
    private Account foggAccount;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        phileasFogg = new Person("Phileas","Fogg");
        foggAccount = new SavingAccount(phileasFogg, new BigDecimal("100"), 10);
    }

    @Test
    public void withdraw() {
        foggAccount.deposit(new BigDecimal("1000"));
        foggAccount.withdraw(new BigDecimal("50"));
        assertEquals("Foggs account must have an amount of 750", new BigDecimal("950"), foggAccount.getBalance());
    }

    @Test
    public void withdrawOverLimit() {
        foggAccount.deposit(new BigDecimal("1000"));

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Withdraw within timeframe not allowed");
        foggAccount.withdraw(new BigDecimal("250.50"));

        fail("Should not be reached!");
    }
}
