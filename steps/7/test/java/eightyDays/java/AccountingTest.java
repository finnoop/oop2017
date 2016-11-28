package eightyDays.java;

import eightyDays.java.account.Current;
import eightyDays.java.account.Saving;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AccountingTest {

    @Test
    public void testSavingAccount() {
        Saving saving = new Saving(BigDecimal.valueOf(500), UUID.randomUUID());
        assertEquals(BigDecimal.ZERO, saving.getBalance());
        assertEquals(BigDecimal.valueOf(400),saving.deposit(BigDecimal.valueOf(400)).getBalance());
        assertEquals(BigDecimal.valueOf(101), saving.deposit(BigDecimal.valueOf(400)).withdraw(BigDecimal.valueOf(299)).getBalance());
    }

    @Test
    public void testSavingOverdraw() {
        Saving saving = new Saving(BigDecimal.valueOf(500), UUID.randomUUID());
        assertEquals(BigDecimal.ZERO, saving.getBalance());
        assertEquals(BigDecimal.valueOf(400),saving.deposit(BigDecimal.valueOf(400)).getBalance());
        assertEquals(BigDecimal.valueOf(100), saving.deposit(BigDecimal.valueOf(400)).withdraw(BigDecimal.valueOf(300)).getBalance());
        try {
            saving.deposit(BigDecimal.valueOf(400)).withdraw(BigDecimal.valueOf(401));
            fail("no exception thrown");
        } catch (RuntimeException exception) {
            assertEquals("Withdraw not possible due to insufficient getBalance", exception.getMessage());
        }
    }

    @Test
    public void testSavingOverWithdrawLimit() {
        Saving saving = new Saving(BigDecimal.valueOf(200), UUID.randomUUID());
        assertEquals(BigDecimal.ZERO, saving.getBalance());
        assertEquals(BigDecimal.valueOf(100), saving.deposit(BigDecimal.valueOf(300)).withdraw(BigDecimal.valueOf(200)).getBalance());
        try {
            saving.deposit(BigDecimal.valueOf(400)).withdraw(BigDecimal.valueOf(201));
            fail("no exception thrown");
        } catch (RuntimeException exception) {
            assertEquals("Value over withdraw limit", exception.getMessage());
        }
    }

    @Test
    public void testCurrent() {
        Current current = new Current(BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40), UUID.randomUUID());
        assertEquals(BigDecimal.ZERO, current.getBalance());
        assertEquals(BigDecimal.valueOf(10.5), current.deposit(BigDecimal.valueOf(12)).getBalance());
        assertEquals(BigDecimal.valueOf(7.0), current.deposit(BigDecimal.valueOf(12)).withdraw(BigDecimal.valueOf(2)).getBalance());
    }
    @Test
    public void testCurrentWithdrawOverLimit() {
        Current current = new Current(BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40), UUID.randomUUID());
        try {
            current.withdraw(BigDecimal.valueOf(400));
            fail("no exception thrown");
        } catch (RuntimeException exception) {
            assertEquals("Value over withdraw limit", exception.getMessage());
        }
    }
}
