package eightyDays.java8.bank.partner;

import java.util.function.Predicate;

public class Person extends Partner {
    private String firstName;

    public Person(String pFirstName, String pName) {
        super(pName);
        firstName = pFirstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public static Predicate<Partner> byFirstName(String firstName) {
        return partner -> ((partner instanceof Person) && ((Person) partner).getFirstName().equals(firstName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        return firstName != null ? firstName.equals(person.firstName) : person.firstName == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}
