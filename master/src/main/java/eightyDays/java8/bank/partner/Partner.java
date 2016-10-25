package eightyDays.java8.bank.partner;

import eightyDays.java8.bank.Bank;

import java.math.BigDecimal;
import java.util.function.Predicate;

public abstract class Partner {
    private String name;

    public Partner(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public BigDecimal asset(Bank bank) {
        // TODO
        return BigDecimal.ONE;
    }

    public static Predicate<Partner> byName(String name) {
        return partner -> partner.getName().equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partner partner = (Partner) o;

        return name.equals(partner.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
