package eightDays.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Bank {
    final private String name;
    private Map<UUID, Person> persons = new HashMap<>();

    public String getName() {
        return name;
    }

    public Bank(String pName) {
        name = pName;
    }

    public UUID addPartner(Person pPerson) {
        return persons.entrySet().stream()
                .filter(entry -> entry.getValue().equals(pPerson))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseGet(() -> {
                    UUID newID = UUID.randomUUID();
                    persons.put(newID, pPerson);
                    return newID;
                });
    }

    public Optional<Person> getPerson(UUID pId) {
        return Optional.ofNullable(persons.get(pId));
    }
}
