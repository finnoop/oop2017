package eightyDays.java8.account;

import eightyDays.java8.Identification;
import eightyDays.java8.Partner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Account {
    public final Identification number = new Identification();

    private final Partner owner;

    private List<Booking> bookings;

    public Account(Partner pOwner, List<Booking> pBookings) {
        owner = pOwner;
        bookings = pBookings;
    }

    public BigDecimal balance() {
        return BigDecimal.ONE;
    }

    public Account withdraw(BigDecimal value, LocalDateTime valuta) {
        return this;
    }

    public Account deposit(BigDecimal value, LocalDateTime valuta) {
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("class=").append(getClass().getSimpleName());
        sb.append(", number=").append(number);
        sb.append(", balance=").append(balance());
        sb.append('}');
        return sb.toString();
    }
}
