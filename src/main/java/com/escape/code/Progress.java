package com.escape.code;
import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;
/*
 * Class used to track progress
 * @Author Erin Check
 */
public class Progress {
    private HashMap<String, Puzzle> puzzlesSolved;
    private int cluesUsed; 
    private ArrayList<Achievement> achievements;

    public Progress(HashMap<String, Puzzle> puzzles, int cluesUsed, ArrayList<Achievement> achievements){
        this.puzzlesSolved = puzzles;
        this.cluesUsed = cluesUsed;
        this.achievements = achievements;
    }

    public HashMap<String, Puzzle> getPuzzlesSolved() {
        return puzzlesSolved;
    }

    public int getCluesUsed() {
        return cluesUsed;
    }

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }
    @Override
    public String toString() {
        String progress = "ID:\nClues Used: "+this.cluesUsed+"\nAchievements:\n";
        for(int i=0; i<this.achievements.size(); i++) {
            progress += this.achievements.get(i).toString();
        }
        for(String line : this.puzzlesSolved.keySet()) {
            progress += "Key: "+line+" Value: "+this.puzzlesSolved.get(line);
        }
        return progress;
    }
}
