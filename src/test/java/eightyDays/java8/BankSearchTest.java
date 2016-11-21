package eightyDays.java8;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BankSearchTest {
    @Test
    public void getName()  {
        Bank testee = new Bank("Test bank");
        Person fogg = new Person("Fogg", "Phileas");
        Person passepartout = new Person("Passepartout", "Jean");
        LegalEntity finnova = new LegalEntity("Finnova", "AG");

        Identification phileas = testee.addPartner(fogg);
        Identification jean = testee.addPartner(passepartout);
        Identification fin = testee.addPartner(finnova);

        assertFalse(testee.searchPerson("Jean").isEmpty());
        assertFalse(testee.searchPartner("Passepartout").isEmpty());

        assertTrue(testee.searchPerson("Finnova").isEmpty());
        assertFalse(testee.searchPartner("Finnova").isEmpty());
    }
}
