package eightyDays.java8.bank.account;

import eightyDays.java8.bank.partner.Identification;
import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public abstract class Account {
    private Partner owner = null;
    private Identification number;
    private List<Booking> bookings = new ArrayList<>();

    Account(Partner owner) {
        this.number = Identification.newIdentification();
        this.owner = owner;
    }

    public Partner getOwner() {
        return owner;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Identification getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return bookings.stream().map(Booking::getAmount).reduce(new BigDecimal(0), BigDecimal::add);
    }

    private void post(BigDecimal value, LocalDateTime valuta,  String text) {
        bookings.add(new Booking(value, valuta, text));
    }

    public void deposit(BigDecimal value) {
        deposit(value, LocalDateTime.now(), "Booking");
    }

    public void deposit(BigDecimal value, LocalDateTime valuta,  String text) {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            post(value, valuta, text);
        } else {
            throw new RuntimeException("Deposit of negative amount not allowed");
        }
    }

    public void withdraw(BigDecimal value) {
        withdraw(value, LocalDateTime.now(), "Booking");
    }

    public void withdraw(BigDecimal value, LocalDateTime valuta,  String text) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            post(value, valuta, text);
        } else {
            throw new RuntimeException("Withdraw of negative or no amount not allowed");
        }
    }

    @Override
    public String toString() {
        return String.format("%s number:%s balance:%.2f", getClass().getSimpleName(), number.getNumber(), getBalance());
    }
}
