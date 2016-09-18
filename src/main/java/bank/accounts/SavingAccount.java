package bank.accounts;

import bank.Customer;

import java.math.BigDecimal;
import java.util.Currency;

public class SavingAccount extends Account {

    SavingAccount(Customer owner, Currency currency, double interest, BigDecimal balance) {
        super(owner, currency, interest, balance);
    }
}
