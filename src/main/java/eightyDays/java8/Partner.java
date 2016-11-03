package eightyDays.java8;

abstract public class Partner {
    private final String name;

    public Partner(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof Partner)) return false;

        Partner partner = (Partner) pO;

        return name != null ? name.equals(partner.name) : partner.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
