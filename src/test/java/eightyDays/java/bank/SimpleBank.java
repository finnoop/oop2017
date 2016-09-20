package eightyDays.java.bank;

import eightyDays.java.bank.partner.Address;
import eightyDays.java.bank.partner.Identification;
import eightyDays.java.bank.partner.Partner;
import eightyDays.java.bank.partner.Person;
import junit.framework.TestCase;
import org.junit.Test;

public class SimpleBank extends TestCase {
    @Test
    public void testFoundBank() {
        Bank out = new Bank("Simple bank");
        assertEquals("Simple bank", out.getName());
        assertEquals(0, out.getPartners().size());
        assertEquals(0, out.getAccounts().size());
    }

    @Test
    public void testPersonOnboarding() {
        Bank out = new Bank("Simple bank");
        assertEquals("Simple bank", out.getName());
        assertEquals(0, out.getPartners().size());

        Partner hansMuster = new Person("Hans","Muster")
                .addAddress(new Address("Sonst wo", "12", "6000", "Luzern"));

        Identification hansMusterId = out.addPartner(hansMuster);

        assertTrue(hansMusterId.getNumber().length() > 0);
        assertEquals(1, out.getPartners().size());
        assertEquals(hansMuster, out.getPartner(hansMusterId));
    }
}
