package eightyDays.java8.bank.account;

import eightyDays.java8.bank.account.rules.LimitedWithdraw;
import eightyDays.java8.bank.account.rules.PerBookingFee;
import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;

public class SavingAccountWithFee extends Account {
    public SavingAccountWithFee(Partner owner, BigDecimal limit, int timeframeInMonths, BigDecimal fee) {
        super(owner);
        addRule(new LimitedWithdraw(limit, timeframeInMonths));
        addRule(new PerBookingFee(fee));
    }
}
