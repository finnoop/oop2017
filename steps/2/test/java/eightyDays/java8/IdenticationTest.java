package eightyDays.java8;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdenticationTest {
    @Test
    public void testGetNumber() throws Exception {
        Identication out = new Identication();
        assertTrue(out.getNumber().toString().length() > 0);
        assertTrue(out.getNumber() != new Identication().getNumber());
    }
}