package eightyDays.java.bank;

import eightyDays.java.bank.partner.Identification;
import eightyDays.java.bank.partner.Partner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bank {
    private String name;
    private Map<Identification, Partner> partners = new HashMap<>();
    private Set<Account> accounts = new HashSet<>();

    public Bank(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public Set<Partner> getPartners() {
        return new HashSet(partners.values());
    }

    public Identification addPartner(Partner pPartners) {
        Identification result = Identification.newIdentification();
        partners.put(result, pPartners);
        return result;
    }

    public void removePartner(Partner pPartners) {
        // tbd remove account of this partner
        partners.remove(pPartners);
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account pAccount) {
        accounts.add(pAccount);
    }

    public void removeAccount(Account pAccount) {
        accounts.remove(pAccount);
    }

    public Partner getPartner(Identification pIdentification) {
        return partners.get(pIdentification);
    }
}
