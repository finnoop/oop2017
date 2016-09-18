package bank;

public class Customer {
    private String firstName;
    private String surName;
    private Contact contact;

    public Customer(String firstName, String surName, Contact contact) {
        this.firstName = firstName;
        this.surName = surName;
        this.contact = contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!firstName.equals(customer.firstName)) return false;
        if (!surName.equals(customer.surName)) return false;
        return contact.equals(customer.contact);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + surName.hashCode();
        result = 31 * result + contact.hashCode();
        return result;
    }
}
