package eightyDays.java8.bank;

import eightyDays.java8.bank.partner.Identification;
import eightyDays.java8.bank.partner.Partner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Bank {
    private String name;
    private Map<Identification, Partner> partners = new HashMap<>();

    public Bank(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public Set<Partner> getPartners() {
        return new HashSet<>(partners.values());
    }

    public Set<Partner> find(Predicate<Partner> predicate) {
        return partners.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter( predicate )
                .collect(Collectors.toSet());
    }

    public Identification addPartner(final Partner pPartner) {
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
}
