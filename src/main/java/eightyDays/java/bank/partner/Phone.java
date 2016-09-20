package eightyDays.java.bank.partner;

public class Phone extends Communication {
    private String number;

    public Phone(String pNumber) {
        number = pNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String pNumber) {
        number = pNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;

        Phone phone = (Phone) o;

        return number != null ? number.equals(phone.number) : phone.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
