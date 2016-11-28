package eightyDays.java.account;

import eightDays.java.account.fee.FeeUtil;
import eightDays.java.account.withdrawal.WithdrawUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Current extends Account {
    private final BigDecimal withdrawLimit;
    private final BigDecimal threshold;
    private final BigDecimal fee;

    public Current(BigDecimal pWithdrawLimit, BigDecimal pFee, BigDecimal pThreshold, UUID pOwner) {
        this(pWithdrawLimit, pFee, pThreshold, pOwner, new ArrayList<>());
    }

    public Current(BigDecimal pWithdrawLimit, BigDecimal pFee, BigDecimal pThreshold, UUID pOwner, ArrayList<Booking> pBookings) {
        super(pOwner, pBookings);
        withdrawLimit = pWithdrawLimit;
        fee = pFee;
        threshold = pThreshold;
    }

    @Override
    public Account post(BigDecimal pValue, LocalDateTime pValuta) {
        WithdrawUtil.limited(withdrawLimit, pValue);
        return new Current(withdrawLimit, fee, threshold, getOwner(), new ArrayList<Booking>(bookings) {{
            add(new Booking(pValue, pValuta));
            Booking additionalBooking = FeeUtil.feeIfLowBalance(getBalance(), threshold, fee, pValuta);
            if (additionalBooking != null) {
                add(additionalBooking);
            }
        }});
    }
}
