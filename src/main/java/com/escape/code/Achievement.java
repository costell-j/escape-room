package com.escape.code;
/*
 * Class used to track achievements
 * @Author Erin Check
 */
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

    public String getTitle() {
        return this.title;
    }

    public boolean isUnlocked() {
        return this.unlocked;
    }

    @Override
    public String toString() {
        return "Title: "+this.title+"\nUnlocked: "+this.unlocked+"/n";
    }
}
