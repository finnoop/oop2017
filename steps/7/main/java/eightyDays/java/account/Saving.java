package eightyDays.java.account;

import eightDays.java.account.withdrawal.Limited;
import eightDays.java.account.withdrawal.NoOverdraw;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Saving extends Account {
    private final BigDecimal withdrawLimit;

    public Saving(BigDecimal pWithdrawLimit, UUID pOwner) {
        this(pOwner, new ArrayList<>(), pWithdrawLimit);
    }

    public Saving(UUID pOwner, List<Booking> pBookings, BigDecimal pWithdrawLimit) {
        super(pOwner, pBookings, (owner, bookings) -> new Saving(owner, bookings, pWithdrawLimit));
        withdrawLimit = pWithdrawLimit;
    }

    @Override
    public Account withdraw(BigDecimal value, LocalDateTime valuta) {
        return new NoOverdraw(
                new Limited(super::withdraw, withdrawLimit).build())
                .build(getBalance())
                .apply(value, valuta);
    }
}
