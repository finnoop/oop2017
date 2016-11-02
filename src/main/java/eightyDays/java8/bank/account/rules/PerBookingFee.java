package eightyDays.java8.bank.account.rules;

import java.math.BigDecimal;

public class PerBookingFee implements BookingRule {

    private BigDecimal fee;

    public PerBookingFee(BigDecimal pFee) {
        this.fee = pFee;
    }

    @Override
    public BigDecimal calculateValue(BigDecimal value) {
        BigDecimal calculatedValue = value;
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            calculatedValue = value.add(fee);
        }
        return calculatedValue;
    }
}
