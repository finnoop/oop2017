package eightDays.java;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bank {
    private String name;
    private Map<UUID, Person> persons = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public Bank(String pName) {

        name = pName;
    }

    public UUID addPartner(Person pPerson) {
        if (persons.containsValue(pPerson)) {
            for(UUID id : persons.keySet()) {
                if (persons.get(id).equals(pPerson)) {
                    return id;
                }
            }
        }
        UUID newId = UUID.randomUUID();
        persons.put(newId, pPerson);
        return newId;
    }

    public Person getPerson(UUID pId) {
        return persons.get(pId);
    }
}
