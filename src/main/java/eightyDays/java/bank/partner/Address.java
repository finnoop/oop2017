package eightyDays.java.bank.partner;

public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;

    public Address(String pStreet, String pHouseNumber, String pZipCode, String pCity) {
        street = pStreet;
        houseNumber = pHouseNumber;
        zipCode = pZipCode;
        city = pCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String pStreet) {
        street = pStreet;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String pHouseNumber) {
        houseNumber = pHouseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String pZipCode) {
        zipCode = pZipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String pCity) {
        city = pCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(address.houseNumber) : address.houseNumber != null) return false;
        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null) return false;
        return city != null ? city.equals(address.city) : address.city == null;
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
