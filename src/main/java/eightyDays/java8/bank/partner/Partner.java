package eightyDays.java8.bank.partner;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

abstract public class Partner {
    private String name;
    private Set<Communication> contatcs = new HashSet<>();
    private Set<Address> addresses = new HashSet<>();

    public Partner(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public Set<Communication> getContatcs() {
        return contatcs;
    }

    public void addContatc(Communication pContact) {
        contatcs.add(pContact);
    }

    public void removeContact(Communication pContact) {
        contatcs.remove(pContact);
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Partner addAddress(Address pAddress) {
        addresses.add(pAddress);
        return this;
    }

    public void removeAddress(Address pAddress) {
        addresses.remove(pAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner)) return false;

        Partner partner = (Partner) o;

        if (name != null ? !name.equals(partner.name) : partner.name != null) return false;
        return contatcs != null ? contatcs.equals(partner.contatcs) : partner.contatcs == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (contatcs != null ? contatcs.hashCode() : 0);
        return result;
    }

    public static Predicate<Partner> byName(String name) {
        return partner -> partner.getName().equals(name);
    }
}
