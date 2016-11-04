package eightyDays.java8;

import eightyDays.java8.account.Current;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

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
        Current current = new Current(jeanPassepartout, BigDecimal.valueOf(100));
        assertEquals(BigDecimal.ZERO, current.getBalance());
        assertEquals(BigDecimal.valueOf(12), current.deposit(BigDecimal.valueOf(12)).getBalance());
        assertEquals(BigDecimal.valueOf(10), current.deposit(BigDecimal.valueOf(12)).withdraw(BigDecimal.valueOf(2)).getBalance());
            /*
    {
        val current = account.Current(100) (1.5, 40)(jeanPassepartout)
        assert (10.5 == = current.deposit(12).getBalance)
        assert (intercept[RuntimeException] {
        current.withdraw(400)
    }.getMessage == = "Withdraw not allowed")
    }
         */
    }

}
