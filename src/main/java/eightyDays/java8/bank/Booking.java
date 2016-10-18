package eightyDays.java8.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
    private LocalDateTime valuta;
    private BigDecimal amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (!valuta.equals(booking.valuta)) return false;
        if (!amount.equals(booking.amount)) return false;
        return text != null ? text.equals(booking.text) : booking.text == null;

    }

    @Override
    public int hashCode() {
        int result = valuta.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    private String text;

    public Booking(BigDecimal amount, LocalDateTime valuta,  String text) {
        this.amount = amount;
        this.valuta = valuta;
        this.text = text;
    }

    public LocalDateTime getValuta() {
        return valuta;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getText() {
        return text;
    }
}
