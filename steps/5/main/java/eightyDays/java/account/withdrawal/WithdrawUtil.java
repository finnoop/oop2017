package eightyDays.java.account.withdrawal;

import java.math.BigDecimal;

public class WithdrawUtil {

    public static void limited(BigDecimal pWithdrawLimit, BigDecimal pValue) {
        if (pValue.compareTo(pWithdrawLimit.negate()) < 0) {
            throw new RuntimeException("Value over withdraw limit");
        }
    }

    public static void noOverdraw(BigDecimal pBalance, BigDecimal pValue) {
        if (pBalance.add(pValue).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Withdraw not possible due to insufficient getBalance");
        }
    }
}
