package eightyDays.java8.bank.partner;

import java.util.UUID;

public class Identification {
    private String number;

    private Identification(String pNumber) {
        number = pNumber;
    }

    public static Identification newIdentification() {
        return new Identification(UUID.randomUUID().toString());
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identification)) return false;

        Identification that = (Identification) o;

        return number != null ? number.equals(that.number) : that.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
