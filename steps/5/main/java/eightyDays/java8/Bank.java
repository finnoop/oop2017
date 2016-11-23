package eightyDays.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Bank {
    private final String name;
    private Map<Identification, Partner> partners = new HashMap<>();

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
}
