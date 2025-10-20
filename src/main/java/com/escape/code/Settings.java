package com.escape.code;
/*
 * Class used to track the settings
 * @Author Erin Check
 */
public class Settings { 
    private int volume;
    private int difficulty;
    /*
     * Creates a new instance of the settings
     * @param volume is the volume
     * @param difficulty is the difficulty
     */
    public Settings(int volume, int difficulty) {
        this.volume = volume;
        this.difficulty = difficulty;
    }

    // Setters
    /*
     * Changes the volume
     * @param volume is the level that the volume will be
     */
    public void changeVolume(int volume) {
        this.volume = volume;
    }
    /*
     * Changes the difficulty
     * @param difficulty is the difficulty that it will be changed to
     */
    public void changeDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    // Getters
    /*
     * returns the volume
     */
    public int getVolume() {
        return volume;
    }
    /*
     * returns the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    // Other Methods
    /*
     * Returns the settings in a String
     */
    @Override
    public String toString() {
        return "Volume: "+this.volume+"\nDifficulty: "+this.difficulty+"\n";
    }
    
}
