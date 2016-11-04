package eightyDays.java8.account.withdrawal;

import eightyDays.java8.account.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class NoOverdraw {

    private final BiFunction<BigDecimal, LocalDateTime, Account> predecessor;

    public NoOverdraw(BiFunction<BigDecimal, LocalDateTime, Account> pPredecessor) {
        predecessor = pPredecessor;
    }

    public BiFunction<BigDecimal, LocalDateTime, Account> apply(BigDecimal pBalance) {
        return (value, valuta) -> {
            if (pBalance.subtract(value).compareTo(BigDecimal.ZERO) < 0)
                throw new RuntimeException("Withdraw not possible due to insufficient getBalance");
            else
                return predecessor.apply(value, valuta);
        };
    }
}