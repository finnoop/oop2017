package eightyDays.java8.bank.partner;

import java.util.function.Predicate;

abstract public class Partner {
    private String name;

    public Partner(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
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

    public static Predicate<Partner> byName(String name) {
        return partner -> partner.getName().equals(name);
    }
}
