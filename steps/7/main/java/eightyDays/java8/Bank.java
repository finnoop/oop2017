package eightyDays.java8;

import java.util.HashMap;
import java.util.Optional;

public class Bank {
    private final String name;
    private HashMap<Identification, Partner> partners = new HashMap<>();

    public Bank(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public Optional<Partner> getPartner(Identification id) {
        return Optional.ofNullable(partners.get(id));
    }
}
