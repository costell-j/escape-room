package com.escape.code;
import java.util.HashMap;
import java.util.ArrayList;
/*
 * Class used to track progress
 * @Author Erin Check
 */
public class Progress {
    private HashMap<String, Puzzle> puzzlesSolved;
    private int cluesUsed; 
    private int completionTime;
    private int currentPuzzle;
    private ArrayList<Achievement> achievements;
    private int currentRoom;

    public Progress(HashMap<String, Puzzle> puzzles, int cluesUsed, int completionTime, int currentPuzzle, ArrayList<Achievement> achievements){
        this.puzzlesSolved = puzzles;
        this.cluesUsed = cluesUsed;
        this.completionTime = completionTime;
        this.currentPuzzle = currentPuzzle;
        this.achievements = achievements;
    }

    // Getters

    public HashMap<String, Puzzle> getPuzzlesSolved() {
        return this.puzzlesSolved;
    }

    public int getCluesUsed() {
        return this.cluesUsed;
    }

    public ArrayList<Achievement> getAchievements() {
        return this.achievements;
    }
    public int getCompletionTime() {
        return this.completionTime;
    }

    public int getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(int room) {
        this.currentRoom = room;
    }

    public int getCurrentPuzzle(){
        return this.currentPuzzle;
    }

    public void setCurrentPuzzle(int puzzle){
        this.currentPuzzle = puzzle;
    }
    @Override
    public String toString() {
        String progress = "Clues Used: "+this.cluesUsed+"\nAchievements:\n";
        for(int i=0; i<this.achievements.size(); i++) {
            progress += this.achievements.get(i).toString();
        }
        for(String line : this.puzzlesSolved.keySet()) {
            progress += "Key: "+line+" Value: "+this.puzzlesSolved.get(line);
        }
        return progress;
    }
}
