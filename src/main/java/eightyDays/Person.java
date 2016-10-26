package eightyDays;

public class Person extends Partner {
    private final String firstName;

    public Person(String pName, String pFirstName) {
        super(pName);
        firstName = pFirstName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof Person)) return false;
        if (!super.equals(pO)) return false;

        Person person = (Person) pO;

        return firstName != null ? firstName.equals(person.firstName) : person.firstName == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}
