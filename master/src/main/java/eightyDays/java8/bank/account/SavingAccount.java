package eightyDays.java8.bank.account;

import eightyDays.java8.bank.account.rules.Limit;
import eightyDays.java8.bank.account.rules.NoWithdraw;
import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public class SavingAccount extends Account {
    public SavingAccount(Partner owner, BigDecimal limit, int timeframeInMonths) {
        super(owner);
        addRule(new Limit(limit, timeframeInMonths));
    }
}
