package com.escape.code;

public class Settings { 
    private int volume;
    private int difficulty;

    public Settings(int volume, int difficulty) {
        this.volume = volume;
        this.difficulty = difficulty;
    }

    public int getVolume() {
        return this.volume;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void changeVolume(int volume) {
        this.volume = volume;
    }
    public void changeDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Volume: "+this.volume+"\nDifficulty: "+this.difficulty+"\n";
    }
    
}
