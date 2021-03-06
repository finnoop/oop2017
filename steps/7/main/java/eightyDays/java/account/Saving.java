package eightyDays.java.account;

import eightyDays.java.account.withdrawal.Limited;
import eightyDays.java.account.withdrawal.NoOverdraw;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class Saving extends Account {
    private final BiFunction<BigDecimal, LocalDateTime, Account> withdrawFunction;

    public Saving(BigDecimal pWithdrawLimit, UUID pOwner) {
        this(pOwner, new ArrayList<>(), pWithdrawLimit);
    }

    public Saving(UUID pOwner, List<Booking> pBookings, BigDecimal pWithdrawLimit) {
        super(pOwner, pBookings, (owner, bookings) -> new Saving(owner, bookings, pWithdrawLimit));
        withdrawFunction =  new NoOverdraw(
                                    new Limited(
                                            super::withdraw, pWithdrawLimit
                                    ).build()
                            ).build(getBalance());
    }

    @Override
    public Account withdraw(BigDecimal value, LocalDateTime valuta) {
        return withdrawFunction.apply(value, valuta);
    }
}
