package com.sins.server.model;

import java.util.List;

public class Group extends PersistentItem {
    
    private List<Person> participants;
    private Person owner;

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants) {
        this.participants = participants;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
    
    public List<String> validateItem() {
        List<String> errors = null;
        return errors;
    }
    
}
