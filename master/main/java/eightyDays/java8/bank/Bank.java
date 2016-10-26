package eightyDays.java8.bank;

import eightyDays.java8.bank.account.Account;
import eightyDays.java8.bank.partner.Identification;
import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Bank {
    private String name;
    private Map<Identification, Partner> partners = new HashMap<>();
    private Map<Identification, Account> accounts = new HashMap<>();

    public Bank(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public Set<Partner> getPartners() {
        return new HashSet<>(partners.values());
    }

    public Set<Account> getAccounts() {
        return new HashSet<>(accounts.values());
    }

    public void deposit(Identification identification, BigDecimal value) {
        accounts.get(identification).deposit(value);
    }

    public void withdraw(Identification identification, BigDecimal value) {
        accounts.get(identification).withdraw(value);
    }

    public Identification add(final Partner pPartner) {
        Identification result;
        if (partners.containsValue(pPartner)) {
            result = partners.entrySet().stream()
                    .filter(keyValue -> pPartner.equals(keyValue.getValue()))
                    .findFirst()
                    .get()
                    .getKey();
        } else {
            result = Identification.newIdentification();
            partners.put(result, pPartner);
        }
        return result;
    }

    public void add(Account account) {
        accounts.put(account.getNumber(), account);
    }

    public Optional<Partner> find(Predicate<Partner> predicate) {
        return partners.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter( predicate )
                .findFirst();
    }

    public Set<Partner> filterPartners(Predicate<Partner> predicate) {
        return partners.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    public Set<Account> filterAccounts(Predicate<Account> predicate) {
        return accounts.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(predicate)
                .collect(Collectors.toSet());
    }
}
