package bank.accounts;

import bank.Address;
import bank.Contact;
import bank.Customer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    Account testee;

    @Before
    public void setup() {
        Address address = new Address("Testingstrasse", "42", "5300", "Scala-Housen");
        Contact contact = new Contact("+41621234567", "+41791234567", "markus.tester@testing.ch", address);
        Customer owner = new Customer("Tester", "Maskurs", contact);

        testee = new SavingAccount(owner, Currency.getInstance("CHF"), 0.1, new BigDecimal(100.00));
    }

    @Test
    public void testDeposit() {
        testee.deposit(new BigDecimal(42));

        assertEquals("After Deposit, amount must be 58.00", new BigDecimal(142.00), testee.getBalance());
    }
    @Test
    public void testWithdraw() {
        testee.withdraw(new BigDecimal(42));

        assertEquals("After Deposit, amount must be 58.00", new BigDecimal(58.00), testee.getBalance());
    }
}
