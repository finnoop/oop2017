package eightyDays.java8;

import eightyDays.java8.account.Account;
import eightyDays.java8.account.Current;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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
                .map(entry -> entry.getKey())
                .orElseGet(() -> {
                    Identification newId = new Identification();
                    partners.put(newId, pPartner);
                    return  newId;
                });
    }

    public Identification openAccount(Identification pOwner, Function<Identification, Account> pFactoryMethod) {
        Account account = pFactoryMethod.apply(pOwner);
        accounts.put(account.getNumber(), account);
        return account.getNumber();
    }
}
