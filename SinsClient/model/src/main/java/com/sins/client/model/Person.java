package com.sins.client.model;

import java.util.List;

public class Person extends PersistentItem {
    
    private boolean isActive;

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public List<String> validateItem() {
        List<String> errors = null;
        return errors;
    }
    
}
