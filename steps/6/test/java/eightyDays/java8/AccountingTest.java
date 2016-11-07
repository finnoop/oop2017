package eightyDays.java8;

import eightyDays.java8.account.Current;
import eightyDays.java8.account.Saving;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AccountingTest {

    private static final Person phileasFogg = new Person("Phileas", "Fogg");
    private static final Person jeanPassepartout = new Person("Jean", "Passepartout");

    @Test
    public void testSavingAccount() {
        Saving saving = new Saving(BigDecimal.valueOf(300), phileasFogg);
        assertEquals(BigDecimal.ZERO, saving.getBalance());
        assertEquals(BigDecimal.valueOf(400),saving.deposit(BigDecimal.valueOf(400)).getBalance());
        assertEquals(BigDecimal.valueOf(101), saving.deposit(BigDecimal.valueOf(400)).withdraw(BigDecimal.valueOf(299)).getBalance());
    }

    @Test
    public void testSavingWithdrawOverLimit() {
        Saving saving = new Saving(BigDecimal.valueOf(300), jeanPassepartout);
        assertEquals(BigDecimal.ZERO, saving.getBalance());
        assertEquals(BigDecimal.valueOf(400),saving.deposit(BigDecimal.valueOf(400)).getBalance());
        assertEquals(BigDecimal.valueOf(100), saving.deposit(BigDecimal.valueOf(400)).withdraw(BigDecimal.valueOf(300)).getBalance());
        try {
            saving.deposit(BigDecimal.valueOf(400)).withdraw(BigDecimal.valueOf(400));
            fail("no exception thrown");
        } catch (RuntimeException exception) {
            assertEquals("Withdraw not allowed", exception.getMessage());
        }
    }

    @Test
    public void testCurrentWithdrawOverLimit() {
        Current current = new Current(BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40), jeanPassepartout);
        assertEquals(BigDecimal.ZERO, current.getBalance());
        assertEquals(BigDecimal.valueOf(10.5), current.deposit(BigDecimal.valueOf(12)).getBalance());
        assertEquals(BigDecimal.valueOf(7.0), current.deposit(BigDecimal.valueOf(12)).withdraw(BigDecimal.valueOf(2)).getBalance());
        try {
            current.withdraw(BigDecimal.valueOf(400));
            fail("no exception thrown");
        } catch (RuntimeException exception) {
            assertEquals("Withdraw not allowed", exception.getMessage());
        }
    }
}
