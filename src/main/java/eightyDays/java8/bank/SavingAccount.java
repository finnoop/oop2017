package eightyDays.java8.bank;

import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;
import java.util.Currency;

public class SavingAccount extends Account {

    SavingAccount(Partner owner, Currency currency, double interest, BigDecimal balance) {
        super(owner, currency, interest, balance);
    }
}
