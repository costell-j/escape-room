package com.escape.code;

public class Achievement {
    private String description;
    private boolean unlocked;

    public Achievement(String description, boolean unlocked){
        this.description = description;
        this.unlocked = unlocked;
    }

    public void unlock() {
        if (unlocked) {
            unlocked = true;
        }
    }
}
