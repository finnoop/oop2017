package eightyDays.java8.account;

import eightyDays.java8.Identification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public abstract class Account {
    public final Identification number = new Identification();

    private final Identification owner;

    private final List<Booking> bookings;

    private BiFunction<Identification, List<Booking>, Account> factoryMethod;

    public Account(Identification pOwner, List<Booking> pBookings, BiFunction<Identification, List<Booking>, Account> pFactoryMethod) {
        owner = pOwner;
        bookings = pBookings;
        factoryMethod = pFactoryMethod;
    }

    public BigDecimal getBalance() {
        return bookings.stream()
                .map(Booking::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Account withdraw(BigDecimal value, LocalDateTime valuta) {
        if (value.compareTo(BigDecimal.ZERO) > 0)
            return post(value.negate(), valuta);
        else
            throw new RuntimeException("Deposit of negative amount not allowed");
    }

    public Account withdraw(BigDecimal value) {
        return withdraw(value, LocalDateTime.now());
    }

    public Account deposit(BigDecimal value, LocalDateTime valuta) {
        if (value.compareTo(BigDecimal.ZERO) > 0)
            return post(value, valuta);
        else
            throw new RuntimeException("Deposit of negative amount not allowed");
    }

    public Account deposit(BigDecimal value) {
        return deposit(value, LocalDateTime.now());
    }

    public Account post(BigDecimal value, LocalDateTime valuta) {
        return factoryMethod.apply(owner, new ArrayList<Booking>(bookings) {{
            add(new Booking(value, valuta));
        }});
    }

    public Identification getNumber() {
        return number;
    }

    public Identification getOwner() {
        return owner;
    }

    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("class=").append(getClass().getSimpleName());
        sb.append(", number=").append(number);
        sb.append(", getBalance=").append(getBalance());
        sb.append('}');
        return sb.toString();
    }
}
