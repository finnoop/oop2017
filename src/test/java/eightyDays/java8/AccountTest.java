package eightyDays.java8;

import eightyDays.java8.account.Current;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AccountTest {

    private static final Person phileasFogg = new Person("Phileas", "Fogg");
    private static final Person jeanPassepartout = new Person("Jean", "Passepartout");

    @Test
    public void testSavingAccount() throws Exception {
        /*
        Saving saving = new Saving(300)(phileasFogg)
        assertEquals(0.0, saving.getBalance)
        assertEquals(400,saving.deposit(400).getBalance)
        assertEquals(101, saving.deposit(400).withdraw(299).getBalance)
        */
    }
/*

        "Withdraw over limit" in {
            val saving = account.Saving(300)(jeanPassepartout)
            assert(0.0 === saving.getBalance)
            assert(400 === saving.deposit(400).getBalance)
            assert(100 === saving.deposit(400).withdraw(300).getBalance)
            assert(intercept[RuntimeException] {
                saving.deposit(400).withdraw(301)
            }.getMessage === "Withdraw not allowed")
        }
    }
    */

    @Test
    public void testCurrentWithdrawOverLimit() {
        Current current = new Current(jeanPassepartout, BigDecimal.valueOf(100), BigDecimal.valueOf(1.5), BigDecimal.valueOf(40));
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
