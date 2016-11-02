package eightyDays.java8.bank.account.rules;

import java.math.BigDecimal;

public class LimitedWithdraw implements BookingRule {
    private BigDecimal limit;
    private int timeframeInMonths;

    public LimitedWithdraw(BigDecimal pLimit, int pTimeframeInMonths) {
        this.limit = pLimit;
        this.timeframeInMonths = pTimeframeInMonths;
    }

    @Override
    public BigDecimal calculateValue(BigDecimal value) {
        if (value.compareTo(limit.negate()) < 0) {
            throw new RuntimeException("Withdraw within timeframe not allowed");
        }

        return value;
    }
}
