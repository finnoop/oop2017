package eightyDays.java;

import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class BankTest {
    @Test
    public void getName() throws Exception {
        Bank testee = new Bank("TestBank");
        assertEquals("TestBank", testee.getName());
    }

    @Test
    public void getPartner() throws Exception {
        Bank testee = new Bank("TestBank");
        assertNull(testee.getPerson(UUID.randomUUID()));
    }

    @Test
    public void testAddCustomer() {
        Bank testee = new Bank("TestBank");
        Person fogg = new Person("Phileas", "Fogg");

        UUID phileas = testee.addPartner(fogg);
        assertEquals(fogg, testee.getPerson(phileas));
        assertEquals(phileas, testee.addPartner(fogg));
    }
}