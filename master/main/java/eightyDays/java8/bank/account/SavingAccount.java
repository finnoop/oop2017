package eightyDays.java8.bank.account;

import eightyDays.java8.bank.account.rules.LimitedWithdraw;
import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;

public class SavingAccount extends Account {
    public SavingAccount(Partner owner, BigDecimal limit, int timeframeInMonths) {
        super(owner);
        addRule(new LimitedWithdraw(limit, timeframeInMonths));
    }
}
