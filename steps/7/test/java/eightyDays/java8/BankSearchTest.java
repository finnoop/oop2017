package eightyDays.java8;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BankSearchTest {
    @Test
    public void getName()  {
        Bank testee = new Bank("Test bank");
        Person fogg = new Person("Fogg", "Phileas");
        Person passepartout = new Person("Passepartout", "Jean");
        LegalEntity finnovaLegalEntity = new LegalEntity("Finnova", "AG");
        LegalEntity netsLegalEntity = new LegalEntity("Nets", "GmbH");

        Identification phileas = testee.addPartner(fogg);
        Identification jean = testee.addPartner(passepartout);
        Identification finnova = testee.addPartner(finnovaLegalEntity);
        Identification nets = testee.addPartner(netsLegalEntity);

        assertFalse(testee.searchPerson("Jean").isEmpty());
        assertEquals(1, testee.searchPerson("Jean").size());
        assertEquals(jean, testee.searchPerson("Jean").iterator().next());

        assertFalse(testee.searchPartner("Passepartout").isEmpty());
        assertEquals(1, testee.searchPartner("Passepartout").size());
        assertEquals(jean, testee.searchPartner("Passepartout").iterator().next());

        assertTrue(testee.searchPerson("Finnova").isEmpty());

        assertFalse(testee.searchPartner("Finnova").isEmpty());
        assertEquals(1, testee.searchPartner("Finnova").size());
        assertEquals(finnova, testee.searchPartner("Finnova").iterator().next());
    }
}
