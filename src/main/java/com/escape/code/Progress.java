package com.escape.code;
import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

public class Progress {
    private UUID room;
    private HashMap<String, Puzzle> puzzlesSolved;
    private int cluesUsed; 
    private ArrayList<Achievement> achievements;

    public Progress(UUID room, HashMap<String, Puzzle> puzzles, int cluesUsed, ArrayList<Achievement> achievements){
        this.room = room;
        this.puzzlesSolved = puzzles;
        this.cluesUsed = cluesUsed;
        this.achievements = achievements;
    }

    @Override
    public String toString() {
        String progress = "ID: "+room+"\nClues Used: "+this.cluesUsed+"\nAchievements:\n";
        for(int i=0; i<this.achievements.size(); i++) {
            progress += this.achievements.get(i).toString();
        }
        for(String line : this.puzzlesSolved.keySet()) {
            progress += "Key: "+line+" Value: "+this.puzzlesSolved.get(line);
        }
        return progress;
    }

    public UUID getRoom() {
        return this.room;
    }

    public HashMap<String, Puzzle> getPuzzlesSolved() {
        return this.puzzlesSolved;
    }

    public int getCluesUsed() {
        return this.cluesUsed;
    }

    public ArrayList<Achievement> getAchievements() {
        return this.achievements;
    }
}
