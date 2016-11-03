package eightyDays.java8.account;

import eightyDays.java8.Partner;

import java.util.List;

public class Current extends Account {
    public Current(Partner pOwner, List<Booking> pBookings) {
        super(pOwner, pBookings);
    }
}
