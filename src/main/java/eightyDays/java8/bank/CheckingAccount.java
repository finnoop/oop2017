package eightyDays.java8.bank;

import eightyDays.java8.bank.partner.Partner;

import java.math.BigDecimal;
import java.util.Currency;

abstract public class CheckingAccount extends Account {

    CheckingAccount(Partner owner) {
        super(owner);
    }
}
