package bank;

public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;

    public Address(String street, String houseNumber, String zipCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    private String city;

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!street.equals(address.street)) return false;
        if (!houseNumber.equals(address.houseNumber)) return false;
        if (!zipCode.equals(address.zipCode)) return false;
        return city.equals(address.city);

    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + houseNumber.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
