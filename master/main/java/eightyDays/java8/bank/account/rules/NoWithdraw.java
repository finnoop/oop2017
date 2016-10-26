package eightyDays.java8.bank.account.rules;

import java.math.BigDecimal;

public class NoWithdraw implements BookingRule {
    @Override
    public BigDecimal calculateValue(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Withdraw not allowed");
        }
        return value;
    }
}
