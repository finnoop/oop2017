package eightyDays.java8.bank.account;

import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;
import java.util.Currency;

public class SavingAccount extends Account {

    public SavingAccount(Partner owner) {
        super(owner);
    }
}
