package eightyDays.java.account.withdrawal;

import eightyDays.java.account.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class Limited {
    private final BigDecimal withdrawLimit;
    private final BiFunction<BigDecimal, LocalDateTime, Account> predecessor;

    public Limited(BiFunction<BigDecimal, LocalDateTime, Account> pPredecessor, BigDecimal pWithdrawLimit) {
        withdrawLimit = pWithdrawLimit;
        predecessor = pPredecessor;
    }

    public BiFunction<BigDecimal, LocalDateTime, Account> build() {
        return (value, valuta) -> {
            if (value.compareTo(withdrawLimit) <= 0)
                return predecessor.apply(value, valuta);
            else
                throw new RuntimeException("Value over withdraw limit");
        };
    }
}
