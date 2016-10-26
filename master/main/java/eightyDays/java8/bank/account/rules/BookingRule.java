package eightyDays.java8.bank.account.rules;

import java.math.BigDecimal;

public interface BookingRule {
    BigDecimal calculateValue(BigDecimal value);
}
