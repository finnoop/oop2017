package eightyDays.java8;

import org.junit.Test;

import static org.junit.Assert.*;

public class LegalEntityTest {
    @Test
    public void testGetForm() throws Exception {
        LegalEntity out = new LegalEntity("name","form");
        assertTrue(out.getName() == "name");
        assertTrue(out.getForm() == "form");
    }

    @Test
    public void testEquals() throws Exception {
        LegalEntity out = new LegalEntity("name","form");
        LegalEntity same = new LegalEntity("name","form");
        LegalEntity other = new LegalEntity("name","other");
        assertTrue(out.equals(out));
        assertTrue(out.equals(same));
        assertFalse(out.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        LegalEntity out = new LegalEntity("name","form");
        LegalEntity same = new LegalEntity("name","form");
        LegalEntity other = new LegalEntity("name","other");
        assertTrue(out.hashCode() == same.hashCode());
        assertFalse(out.hashCode() == other.hashCode());
    }
}