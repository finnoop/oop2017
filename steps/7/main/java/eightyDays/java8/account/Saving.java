package eightyDays.java8.account;

import eightyDays.java8.Identification;
import eightyDays.java8.Partner;
import eightyDays.java8.account.withdrawal.Limited;
import eightyDays.java8.account.withdrawal.NoOverdraw;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Saving extends Account {
    private final BigDecimal withdrawLimit;

    public Saving(BigDecimal pWithdrawLimit, Identification pOwner) {
        this(pOwner, new ArrayList<>(), pWithdrawLimit);
    }

    public Saving(Identification pOwner, List<Booking> pBookings, BigDecimal pWithdrawLimit) {
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
