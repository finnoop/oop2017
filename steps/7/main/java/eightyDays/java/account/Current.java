package eightyDays.java.account;

import eightyDays.java.account.fee.LowBalancePerBooking;
import eightyDays.java.account.withdrawal.Limited;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class Current extends Account {
    private final BigDecimal withdrawLimit;
    private final BigDecimal threshold;
    private final BigDecimal fee;

    public Current(BigDecimal pWithdrawLimit, BigDecimal pFee, BigDecimal pThreshold, UUID pOwner) {
        this(pOwner, new ArrayList<>(), pWithdrawLimit, pFee, pThreshold);
    }

    public Current(UUID pOwner, List<Booking> pBookings, BigDecimal pWithdrawLimit, BigDecimal pFee, BigDecimal pThreshold) {
        super(pOwner, pBookings, (owner, bookings) -> new Current(owner, bookings, pWithdrawLimit, pFee, pThreshold));
        withdrawLimit = pWithdrawLimit;
        threshold = pThreshold;
        fee = pFee;
    }

    @Override
    public Account withdraw(BigDecimal value, LocalDateTime valuta) {
        return postDecorator(super::withdraw, value, valuta);
    }

    @Override
    public Account deposit(BigDecimal value, LocalDateTime valuta) {
        return postDecorator(super::deposit, value, valuta);
    }

    private Account postDecorator(BiFunction<BigDecimal, LocalDateTime, Account> pPredecessor, BigDecimal value, LocalDateTime valuta) {
        return new LowBalancePerBooking(
                new Limited(pPredecessor, withdrawLimit).build(),
                    threshold, fee).build()
                .apply(value, valuta);
    }
}

