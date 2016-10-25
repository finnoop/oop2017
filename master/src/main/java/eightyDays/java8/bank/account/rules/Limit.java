package eightyDays.java8.bank.account.rules;

import java.math.BigDecimal;

public class Limit implements BookingRule {
    private BigDecimal limit;
    private int timeframeInMonths;

    public Limit(BigDecimal limit, int timeframeInMonths) {
        this.limit = limit;
        this.timeframeInMonths = timeframeInMonths;
    }

    @Override
    public BigDecimal calculateValue(BigDecimal value) {
        if (value.compareTo(limit) < 0) {
            throw new RuntimeException("Withdraw within timeframe not allowed");
        }

        return value;
    }
}
