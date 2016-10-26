package eightyDays;

import java.util.HashMap;
import java.util.Optional;

public class Bank {
    private final String name;
    private HashMap<Identication, Partner> partners = new HashMap<>();

    public Bank(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public Optional<Partner> getPartner(Identication id) {
        return Optional.of(partners.get(id));
    }
}
