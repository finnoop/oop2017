package eightyDays.java;

public class Person {
    private final String name;
    private final String firstName;

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof Person)) return false;

        Person person = (Person) pO;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return firstName != null ? firstName.equals(person.firstName) : person.firstName == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }

    public String getName() {

        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person(String pName, String pFirstName) {
        name = pName;
        firstName = pFirstName;
    }
}
