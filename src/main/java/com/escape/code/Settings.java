package com.escape.code;
/*
 * Class used to track the settings
 * @Author Erin Check
 */
public class Settings { 
    private int volume;
    private int difficulty;

    public Settings(int volume, int difficulty) {
        this.volume = volume;
        this.difficulty = difficulty;
    }

    // Setters

    public void changeVolume(int volume) {
        this.volume = volume;
    }
    public void changeDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    // Getters

    public int getVolume() {
        return volume;
    }

    public int getDifficulty() {
        return difficulty;
    }

    // Other Methods

    @Override
    public String toString() {
        return "Volume: "+this.volume+"\nDifficulty: "+this.difficulty+"\n";
    }
    
}
