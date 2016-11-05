package eightyDays.java8;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdentificationTest {
    @Test
    public void testGetNumber() throws Exception {
        Identification out = new Identification();
        assertTrue(out.getNumber().toString().length() > 0);
        assertTrue(out.getNumber() != new Identification().getNumber());
    }
}