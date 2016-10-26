package eightyDays.java8.bank.account;

import eightyDays.java8.bank.account.rules.NoWithdraw;
import eightyDays.java8.bank.partner.Partner;

public class FixedRateMortgage extends Account {
    public FixedRateMortgage(Partner owner) {
        super(owner);
        addRule(new NoWithdraw());
    }
}
