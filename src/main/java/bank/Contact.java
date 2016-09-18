package bank;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String phoneNumber;
    private String mobileNumber;
    private String email;
    private Address mainAddress;
    private List<Address> addresses = new ArrayList<>();

    public Contact(String phoneNumber, String mobileNumber, String email, Address address) {
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.mainAddress = address;
        this.addresses.add(address);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getMainAddress() {
        return mainAddress;
    }

    public List<Address> getAddresses() { return addresses; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (phoneNumber != null ? !phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(contact.mobileNumber) : contact.mobileNumber != null)
            return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        return mainAddress.equals(contact.mainAddress);

    }

    @Override
    public int hashCode() {
        int result = phoneNumber != null ? phoneNumber.hashCode() : 0;
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + mainAddress.hashCode();
        return result;
    }
}
