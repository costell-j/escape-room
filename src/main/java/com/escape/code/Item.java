package com.escape.code;

public class Item {
    
    private String name;
    private String description;
    private boolean used;

    public Item(String name, String description, boolean used) {
        this.name = name;
        this.description = description;
        this.used = used;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUsed() {
        return this.used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    
}
