package eightyDays.java.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public abstract class Account {
    private final UUID owner;

    protected final List<Booking> bookings;

    private BiFunction<UUID, List<Booking>, Account> factoryMethod;

    public Account(UUID pOwner, List<Booking> pBookings, BiFunction<UUID, List<Booking>, Account> pFactoryMethod) {
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

    public UUID getOwner() {
        return owner;
    }

    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }
}
