package eightyDays.java8.bank;

import eightyDays.Testdata;
import eightyDays.java8.bank.partner.Address;
import eightyDays.java8.bank.partner.Identification;
import eightyDays.java8.bank.partner.Partner;
import eightyDays.java8.bank.partner.Person;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SimpleBank {
    @Test
    public void testFoundBank() {
        Bank out = new Bank("Simple bank");
        assertEquals("Simple bank", out.getName());
        assertEquals(0, out.getPartners().size());
        assertEquals(0, out.getAccounts().size());
    }

    @Test
    public void testPersonOnboarding2() {
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

    @Test
    public void testSearchPersonWithEmptyBank() {
        Bank out = new Bank("Simple bank");
        assertEquals("Simple bank", out.getName());
        assertEquals(0, out.getPartners().size());

        Set<Partner> result = out.findByName("Muster");
        assertNotEquals(null, result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchPersonWithBigBank() {
        Bank out = bigBank();
        assertTrue(out.getPartners().size() > 10);

        Set<Partner> result = out.findByName("Muster");

        assertNotEquals(null, result);
        assertEquals(0, result.size());
    }

    private Bank bigBank() {
        Bank bigBank = new Bank("Simple bank");
        Testdata testdata =new Testdata();
        testdata.personsJ(11).forEach(bigBank::addPartner);
        return bigBank;
    }
}
