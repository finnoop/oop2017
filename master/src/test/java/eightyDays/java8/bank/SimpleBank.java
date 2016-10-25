package eightyDays.java8.bank;

import eightyDays.java8.bank.account.Account;
import eightyDays.java8.bank.account.SavingAccount;
import eightyDays.java8.bank.partner.Identification;
import eightyDays.java8.bank.partner.Partner;
import eightyDays.java8.bank.partner.Person;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class SimpleBank {

    private Partner phileasFogg;
    private Partner aoudaFogg;

    @Before
    public void init() {
        phileasFogg = new Person("Phileas","Fogg");
        aoudaFogg = new Person("Aouda","Fogg");
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

        Identification phileasFoggId = out.add(phileasFogg);

        assertTrue(phileasFoggId.getNumber().length() > 0);
        assertEquals(1, out.getPartners().size());
    }

    @Test
    public void testSearchPartnerWithEmptyBank() {
        Bank out = new Bank("Bank of England");
        assertEquals("Bank of England", out.getName());
        assertEquals(0, out.getPartners().size());

        Optional<Partner> result = out.find(p -> p.getName().equals("Fogg"));
        assertNotEquals(null, result);
        assertFalse(result.isPresent());
    }

    @Test
    public void testSearchPersonByName() {
        Bank out = new Bank("Bank of England");
        out.add(phileasFogg);
        out.add(aoudaFogg);

        Set<Partner> result = out.filterPartners(Person.byName("Fogg"));
        assertEquals(2, result.size());
        assertTrue(result.contains(phileasFogg));
        assertTrue(result.contains(aoudaFogg));
    }

    @Test
    public void testSearchAccount() {
        Bank out = new Bank("Bank of England");
        Account account = new SavingAccount(phileasFogg, new BigDecimal(100), 10);
        out.add(account);

        Set<Account> result = out.filterAccounts(a -> a.getOwner().equals(phileasFogg));
        assertEquals(1, result.size());
        assertTrue(result.contains(account));
    }

    @Test
    public void testSearchAccountReturnTwo() {
        Bank out = new Bank("Bank of England");
        Account account1 = new SavingAccount(phileasFogg, new BigDecimal(100), 10);
        Account account2 = new SavingAccount(phileasFogg, new BigDecimal(100), 10);
        out.add(account1);
        out.add(account2);

        Set<Account> result = out.filterAccounts(a -> a.getOwner().equals(phileasFogg));
        assertEquals(2, result.size());
        assertTrue(result.contains(account1));
        assertTrue(result.contains(account2));
    }

    @Test
    public void testSearchPersonByFirstName() {
        Bank out = new Bank("Bank of England");
        out.add(phileasFogg);
        out.add(aoudaFogg);

        Optional<Partner> result = out.find(Person.byFirstName("Phileas"));
        assertTrue(result.isPresent());
        assertTrue(result.get().equals(phileasFogg));
    }

    @Test
    public void testSearchPartnerWithBigBank() {
        Bank out = bigBank();
        assertTrue(out.getPartners().size() > 10);

        Optional<Partner> result = out.find(Partner.byName("Fogg"));

        assertNotEquals(null, result);
        assertFalse(result.isPresent());
    }

    private Bank bigBank() {
        Bank bigBank = new Bank("Bank of England");

        eightyDays.scala211.Testdata testdata = new eightyDays.scala211.Testdata();
        testdata.personsJ(11).forEach(bigBank::add);
        return bigBank;
    }
}
