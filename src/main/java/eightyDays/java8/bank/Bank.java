package eightyDays.java8.bank;

import eightyDays.java8.bank.partner.Identification;
import eightyDays.java8.bank.partner.Partner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Identification addPartner(final Partner pPartner) {
        Identification result;
        if (partners.containsValue(pPartner)) {
            result = partners.entrySet().stream().filter(keyValue -> pPartner.equals(keyValue.getValue())).findFirst().get().getKey();
        } else {
            result = Identification.newIdentification();
            partners.put(result, pPartner);
        }
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

    public Set<Partner> searchPartners(String pName) {
        return partners.entrySet().stream()
                .filter(entry -> entry.getValue().getName().equals(pName))
                .map(entry -> entry.getValue())
                .collect(Collectors.toSet());
    }
}
