package eightyDays.java.account;

import eightyDays.java.account.withdrawal.WithdrawUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Saving extends Account {
    private final BigDecimal withdrawLimit;

    public Saving(BigDecimal pWithdrawLimit, UUID pOwner) {
        this(pWithdrawLimit, pOwner, new ArrayList<>());
    }

    public Saving(BigDecimal pWithdrawLimit, UUID pOwner, ArrayList<Booking> pBookings) {
        super(pOwner, pBookings);
        withdrawLimit = pWithdrawLimit;
    }

    @Override
    public Account post(BigDecimal pValue, LocalDateTime pValuta) {
        WithdrawUtil.noOverdraw(getBalance(), pValue);
        WithdrawUtil.limited(withdrawLimit, pValue);
        return new Saving(withdrawLimit, getOwner(), new ArrayList<Booking>(bookings) {{
            add(new Booking(pValue, pValuta));
        }});
    }
}
