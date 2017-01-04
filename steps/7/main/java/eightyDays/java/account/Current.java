package eightyDays.java.account;

import eightyDays.java.account.fee.LowBalancePerBooking;
import eightyDays.java.account.withdrawal.Limited;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Current extends Account {
    private final Function<BiFunction<BigDecimal, LocalDateTime, Account>, BiFunction<BigDecimal, LocalDateTime, Account>> postFunction;

    public Current(BigDecimal pWithdrawLimit, BigDecimal pFee, BigDecimal pThreshold, UUID pOwner) {
        this(pOwner, new ArrayList<>(), pWithdrawLimit, pFee, pThreshold);
    }

    public Current(UUID pOwner, List<Booking> pBookings, BigDecimal pWithdrawLimit, BigDecimal pFee, BigDecimal pThreshold) {
        super(pOwner, pBookings, (owner, bookings) -> new Current(owner, bookings, pWithdrawLimit, pFee, pThreshold));
        postFunction = (superFunction) -> new LowBalancePerBooking(
                                                new Limited(
                                                        superFunction, pWithdrawLimit
                                                ).build(),
                                          pThreshold, pFee).build();
    }

    @Override
    public Account withdraw(BigDecimal value, LocalDateTime valuta) {
        return postFunction.apply(super::withdraw).apply(value, valuta);
    }

    @Override
    public Account deposit(BigDecimal value, LocalDateTime valuta) {
        return postFunction.apply(super::deposit).apply(value, valuta);
    }
}