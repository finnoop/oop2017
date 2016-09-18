package bank.accounts;

import java.math.BigDecimal;
import java.util.Date;

public class Statement {
    Date date;
    BigDecimal amount;

    public Statement(Date date, BigDecimal amount) {
        this.date = date;
        this.amount = amount;
    }
}
