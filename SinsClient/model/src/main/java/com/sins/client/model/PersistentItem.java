package com.sins.client.model;

import java.util.List;

public class PersistentItem {
    
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> validateItem() {
        List<String> errors = null;
        return errors;
    }
    
}
