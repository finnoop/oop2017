package eightyDays.java.account.fee;

import eightDays.java.account.Booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FeeUtil {
    public static Booking feeIfLowBalance(BigDecimal pBalance, BigDecimal pThreshold, BigDecimal pFee, LocalDateTime pValuta) {
        if (pBalance.compareTo(pThreshold) < 0) {
            return new Booking(pFee.negate(), pValuta);
        } else {
            return null;
        }
    }
}
