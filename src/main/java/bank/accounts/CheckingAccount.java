package bank.accounts;

import bank.Customer;

import java.math.BigDecimal;
import java.util.Currency;

public class CheckingAccount extends Account {

    CheckingAccount(Customer owner, Currency currency, double interest, BigDecimal balance) {
        super(owner, currency, interest, balance);
    }
}
