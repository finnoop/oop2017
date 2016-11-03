package eightyDays.java8;

import java.util.UUID;

public class Identification {
    private final UUID number = UUID.randomUUID();

    public UUID getNumber() {
        return number;
    }
}
