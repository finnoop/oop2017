package eightDays.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void testGetFirstName() throws Exception {
        Person out = new Person("name","firstName");
        assertEquals(out.getFirstName(), "firstName");
        assertEquals(out.getName(), "name");
    }

    @Test
    public void testEquals() throws Exception {
        Person out = new Person("name","firstName");
        Person same = new Person("name","firstName");
        Person other = new Person("name","other");
        assertTrue(out.equals(out));
        assertTrue(out.equals(same));
        assertFalse(out.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Person out = new Person("name","firstName");
        Person same = new Person("name","firstName");
        Person other = new Person("name","other");
        assertEquals(out.hashCode(), same.hashCode());
        assertNotEquals(out.hashCode(), other.hashCode());
    }
}