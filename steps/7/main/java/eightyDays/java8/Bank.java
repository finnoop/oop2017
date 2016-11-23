package eightyDays.java8;

import eightyDays.java8.account.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Bank {
    private final String name;
    private Map<Identification, Partner> partners = new HashMap<>();
    private Map<Identification, Account> accounts = new HashMap<>();

    public Bank(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public Optional<Partner> getPartner(Identification id) {
        return Optional.ofNullable(partners.get(id));
    }

    public Identification addPartner(Partner pPartner) {
        return partners
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(pPartner))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseGet(() -> {
                    Identification newId = new Identification();
                    partners.put(newId, pPartner);
                    return newId;
                });
    }

    public Account openAccount(Identification pOwner, Function<Identification, Account> pFactoryMethod) {
        Account account = pFactoryMethod.apply(pOwner);
        accounts.put(account.getNumber(), account);
        return account;
    }

    protected Account post(Identification account, Function<Account, Account> action) {
        Account updatedAccount = action.apply(accounts.get(account));
        accounts.remove(account);
        accounts.put(account, updatedAccount);
        return updatedAccount;
    }

    public Account deposit(Identification pId, BigDecimal pValue) {
        return deposit(pId, pValue, LocalDateTime.now());
    }

    public Account deposit(Identification pId, BigDecimal pValue, LocalDateTime pValuta) {
        return post(pId, account -> account.deposit(pValue, pValuta));
    }

    public Account withdraw(Identification pId, BigDecimal pValue) {
        return withdraw(pId, pValue, LocalDateTime.now());
    }

    public Account withdraw(Identification pId, BigDecimal pValue, LocalDateTime pValuta) {
        return post(pId, account -> account.withdraw(pValue, pValuta));
    }

    public Optional<Account> getAccount(Identification pId) {
        return Optional.ofNullable(accounts.get(pId));
    }

    public BigDecimal getAssets(Identification pOwner) {
        return accounts.entrySet().stream()
                .filter(entry -> entry.getValue().getOwner().equals(pOwner))
                .map(entry -> entry.getValue().getBalance())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected Set<Identification> getPartner(Predicate<Partner> predicate) {
        return partners.entrySet().stream()
                .filter(entry -> predicate.test(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Set<Identification> searchPartner(String pName) {
        return getPartner(partner -> partner.getName().equals(pName));
    }

    public Set<Identification> searchPerson(String pFirstname) {
        return getPartner(partner -> {
            if (partner instanceof Person) {
                return ((Person)partner).getFirstName().equals(pFirstname);
            } else {
                return false;
            }
        });
    }
}
