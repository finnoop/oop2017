package eightyDays.java8;

import org.junit.Test;

import java.util.Optional;

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
        assertEquals(Optional.empty(), testee.getPartner(new Identification()));
    }

    @Test
    public void testAddCustomer() {
        Bank testee = new Bank("TestBank");
        Person fogg = new Person("Phileas", "Fogg");

        Identification phileas = testee.addPartner(fogg);
        assertEquals(Optional.of(fogg), testee.getPartner(phileas));
        assertEquals(phileas, testee.addPartner(fogg));
    }
}