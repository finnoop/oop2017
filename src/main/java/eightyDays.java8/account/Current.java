package eightyDays.java8.account;

import eightyDays.java8.Partner;
import eightyDays.java8.account.withdrawal.Limited;
import eightyDays.java8.account.withdrawal.NoOverdraw;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Current extends Account {
    private final BigDecimal withdrawLimit;

    public Current(Partner pOwner, BigDecimal pWithdrawLimit) {
        this(pOwner, new ArrayList<>(), pWithdrawLimit);
    }

    public Current(Partner pOwner, List<Booking> pBookings, BigDecimal pWithdrawLimit) {
        super(pOwner, pBookings, (owner, bookings) -> new Current(owner, bookings, pWithdrawLimit));
        withdrawLimit = pWithdrawLimit;
    }

    @Override
    public Account withdraw(BigDecimal value, LocalDateTime valuta) {
        return new NoOverdraw(
                new Limited(super::withdraw, withdrawLimit).apply())
                .apply(getBalance())
                .apply(value, valuta);
    }
}

