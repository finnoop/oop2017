package eightyDays.java8;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BankTest {
    @Test
    public void getName() throws Exception {
        Bank out = new Bank("TestBank");
        assertEquals("TestBank", out.getName());
    }

    @Test
    public void getPartner() throws Exception {
        Bank out = new Bank("TestBank");
        assertEquals(Optional.empty(), out.getPartner(new Identification()));
    }

    @Test
    public void addPartner() throws Exception {
        Bank out = new Bank("TestBank");
        Partner fogg = new Person("Phileas", "Fogg");
        Identification phileas = out.addPartner(fogg);
        assertEquals(Optional.of(fogg), out.getPartner(phileas));
        assertEquals(phileas, out.addPartner(fogg));
    }
}