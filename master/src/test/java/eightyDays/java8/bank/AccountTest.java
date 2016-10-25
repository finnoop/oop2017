package eightyDays.java8.bank;


import eightyDays.java8.bank.account.Account;
import eightyDays.java8.bank.account.SavingAccount;
import eightyDays.java8.bank.partner.Partner;
import eightyDays.java8.bank.partner.Person;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private Partner phileasFogg;
    private Account foggAccount;

    @Before
    public void init() {
        phileasFogg = new Person("Phileas","Fogg");
        foggAccount = new SavingAccount(phileasFogg);
    }

    @Test
    public void oneBooking() {
        foggAccount.deposit(new BigDecimal(1000));

        assertEquals("Foggs account must have one booking", 1, foggAccount.getBookings().size());
    }

    @Test
    public void twoBookings() {
        foggAccount.deposit(new BigDecimal("500"));
        foggAccount.deposit(new BigDecimal("1000"));

        assertEquals("Foggs account must have one booking", 2, foggAccount.getBookings().size());
    }

    @Test
    public void balance() {
        foggAccount.deposit(new BigDecimal("500.25"));
        foggAccount.deposit(new BigDecimal("1000.25"));
        assertEquals("Foggs account must have an amount of 1500.50", new BigDecimal("1500.50"), foggAccount.getBalance());
    }
}
