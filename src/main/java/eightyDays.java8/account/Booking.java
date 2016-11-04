package eightyDays.java8.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
    private final BigDecimal amount;
    private final LocalDateTime valuta;

    public Booking(BigDecimal pAmount, LocalDateTime pValuta) {
        amount = pAmount;
        valuta = pValuta;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getValuta() {
        return valuta;
    }

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof Booking)) return false;

        Booking booking = (Booking) pO;

        if (amount != null ? !amount.equals(booking.amount) : booking.amount != null) return false;
        return valuta != null ? valuta.equals(booking.valuta) : booking.valuta == null;

    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (valuta != null ? valuta.hashCode() : 0);
        return result;
    }
}
