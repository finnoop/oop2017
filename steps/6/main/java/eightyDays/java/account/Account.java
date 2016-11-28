package eightyDays.java.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Account {
    private final UUID owner;

    protected final List<Booking> bookings;

    public Account(UUID pOwner, List<Booking> pBookings) {
        owner = pOwner;
        bookings = pBookings;
    }

    public Account(UUID pOwner) {
        this(pOwner, new ArrayList<>());
    }

    public BigDecimal getBalance() {
        return bookings.stream()
                .map(Booking::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Account withdraw(BigDecimal pValue, LocalDateTime pValuta) {
        if (pValue.compareTo(BigDecimal.ZERO) > 0)
            return post(pValue.negate(), pValuta);
        else
            throw new RuntimeException("Deposit of negative amount not allowed");
    }

    public Account withdraw(BigDecimal pValue) {
        return withdraw(pValue, LocalDateTime.now());
    }

    public Account deposit(BigDecimal pValue, LocalDateTime pValuta) {
        if (pValue.compareTo(BigDecimal.ZERO) > 0)
            return post(pValue, pValuta);
        else
            throw new RuntimeException("Deposit of negative amount not allowed");
    }

    public Account deposit(BigDecimal pValue) {
        return deposit(pValue, LocalDateTime.now());
    }

    public abstract Account post(BigDecimal pValue, LocalDateTime pValuta);

    public UUID getOwner() {
        return owner;
    }
}
