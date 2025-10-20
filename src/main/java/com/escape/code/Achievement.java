package com.escape.code;
/*
 * Class used to track achievements
 * @Author Erin Check
 */
public class Achievement {
    private String title;
    private boolean unlocked;
    /*
     * Creates a new instance of the Achievement
     * @param title is the title of the achievement
     * @param unlocked returns a boolean based on if the achievement has been unlocked
     */
    public Achievement(String title, boolean unlocked){
        this.title = title;
        this.unlocked = unlocked;
    }

    // Getters
    /*
     * Returns the title
     */
    public String getTitle() {
        return this.title;
    }
    /*
     * Returns whether or not the achievement has been unlocked
     */
    public boolean isUnlocked() {
        return this.unlocked;
    }

    // Functionality Methods
    /*
     * Unlocks the achievement  
     */
    public void unlock() {
        if (unlocked) {
            unlocked = true;
        }
    }
    /*
     * Returns the achievement in a String
     */
    @Override
    public String toString() {
        return "Title: "+this.title+"\nUnlocked: "+this.unlocked+"/n";
    }
}
