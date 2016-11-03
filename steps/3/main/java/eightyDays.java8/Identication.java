package eightyDays;

import java.util.UUID;

public class Identication {
    private final UUID number = UUID.randomUUID();

    public UUID getNumber() {
        return number;
    }
}
