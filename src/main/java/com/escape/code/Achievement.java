package com.escape.code;

public class Achievement {
    private String title;
    private boolean unlocked;

    public Achievement(String title, boolean unlocked){
        this.title = title;
        this.unlocked = unlocked;
    }

    public void unlock() {
        if (unlocked) {
            unlocked = true;
        }
    }

    public String getTitle(){
        return title;
    }

    public boolean getUnlocked(){
        return unlocked;
    }

    @Override
    public String toString() {
        return "Title: "+this.title+"\nUnlocked: "+this.unlocked+"/n";
    }
}
