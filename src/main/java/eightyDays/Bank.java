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

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof Bank)) return false;

        Bank bank = (Bank) pO;

        if (name != null ? !name.equals(bank.name) : bank.name != null) return false;
        return partners != null ? partners.equals(bank.partners) : bank.partners == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (partners != null ? partners.hashCode() : 0);
        return result;
    }
}
