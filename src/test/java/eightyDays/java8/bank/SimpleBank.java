package eightyDays.java8.bank;

import eightyDays.Testdata;
import eightyDays.java8.bank.partner.Address;
import eightyDays.java8.bank.partner.Identification;
import eightyDays.java8.bank.partner.Partner;
import eightyDays.java8.bank.partner.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class SimpleBank {

    private Partner phileasFogg;
    private Partner aoudaFogg;

    @Before
    public void init() {
        phileasFogg = new Person("Phileas","Fogg")
                .addAddress(new Address("Savile Row", "7", "WC2N", "London"));

        aoudaFogg = new Person("Aouda","Fogg")
                .addAddress(new Address("Savile Row", "7", "WC2N", "London"));
    }

    @Test
    public void testListOfPartners() {
        List<Partner> partners = new ArrayList<Partner>();
    }

    @Test
    public void testFoundBank() {
        Bank out = new Bank("Bank of England");
        assertEquals("Bank of England", out.getName());
        assertEquals(0, out.getPartners().size());
    }

    @Test
    public void testPersonOnboarding() {
        Bank out = new Bank("Bank of England");
        assertEquals("Bank of England", out.getName());
        assertEquals(0, out.getPartners().size());

        Identification phileasFoggId = out.addPartner(phileasFogg);

        assertTrue(phileasFoggId.getNumber().length() > 0);
        assertEquals(1, out.getPartners().size());
    }

    @Test
    public void testSearchPartnerWithEmptyBank() {
        Bank out = new Bank("Bank of England");
        assertEquals("Bank of England", out.getName());
        assertEquals(0, out.getPartners().size());

        Set<Partner> result = out.find( p -> p.getName().equals("Fogg"));
        assertNotEquals(null, result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchPersonByName() {
        Bank out = new Bank("Bank of England");
        out.addPartner(phileasFogg);
        out.addPartner(aoudaFogg);

        Set<Partner> result = out.find(Person.byName("Fogg"));
        assertEquals(2, result.size());
        assertTrue(result.contains(phileasFogg));
        assertTrue(result.contains(aoudaFogg));
    }

    @Test
    public void testSearchPersonByFirstName() {
        Bank out = new Bank("Bank of England");
        out.addPartner(phileasFogg);
        out.addPartner(aoudaFogg);

        Set<Partner> result = out.find(Person.byFirstName("Phileas"));
        assertEquals(1, result.size());
        assertTrue(result.contains(phileasFogg));
    }

    @Test
    public void testSearchPartnerWithBigBank() {
        Bank out = bigBank();
        assertTrue(out.getPartners().size() > 10);

        Set<Partner> result = out.find(Partner.byName("Fogg"));

        assertNotEquals(null, result);
        assertEquals(0, result.size());
    }

    private Bank bigBank() {
        Bank bigBank = new Bank("Bank of England");
        Testdata testdata = new Testdata();
        testdata.personsJ(11).forEach(bigBank::addPartner);
        return bigBank;
    }
}
