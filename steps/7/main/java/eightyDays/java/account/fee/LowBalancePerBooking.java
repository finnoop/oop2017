package eightyDays.java.account.fee;

import eightDays.java.account.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class LowBalancePerBooking {
    private final BigDecimal fee;
    private final BigDecimal threshold;
    private final BiFunction<BigDecimal, LocalDateTime, Account> predecessor;

    public LowBalancePerBooking(BiFunction<BigDecimal, LocalDateTime, Account> pPredecessor, BigDecimal pThreshold, BigDecimal pFee) {
        threshold = pThreshold;
        fee = pFee;
        predecessor = pPredecessor;
    }

    public BiFunction<BigDecimal, LocalDateTime, Account> build() {
        return (value, valuta) -> {
            Account booked = predecessor.apply(value, valuta);
            if (booked.getBalance().compareTo(threshold) <= 0)
                return booked.post(fee.negate(), valuta);
            else
                return booked;
        };
    }
}
