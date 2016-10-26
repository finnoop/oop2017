package eightyDays.java8.bank.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
    private BigDecimal value;
    private LocalDateTime valuta;
    private String text;

    public Booking(BigDecimal value, LocalDateTime valuta, String text) {
        this.value = value;
        this.valuta = valuta;
        this.text = text;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getValuta() {
        return valuta;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (!value.equals(booking.value)) return false;
        if (!valuta.equals(booking.valuta)) return false;
        return text != null ? text.equals(booking.text) : booking.text == null;

    }

    @Override
    public int hashCode() {
        int result = valuta.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
