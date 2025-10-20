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


    public Progress() {
        this.puzzlesSolved = new HashMap<>();
        this.cluesUsed = 0;
        this.completionTime = 0;
        this.currentPuzzle = 0;
        this.achievements = new ArrayList<>();
    }
    /*
     * Creates a new instance of the progress
     * @param puzzles is the puzzles
     * @param cluesUsed is the amount of clues the user has used
     * @param completionTime is the amount of time the puzzles took to complete
     * @param achievements is the achievements the user has recieved
     */
    public Progress(HashMap<String, Puzzle> puzzles, int cluesUsed, int completionTime, int currentPuzzle, ArrayList<Achievement> achievements){
        this.puzzlesSolved = puzzles;
        this.cluesUsed = cluesUsed;
        this.completionTime = completionTime;
        this.currentPuzzle = currentPuzzle;
        this.achievements = achievements;
    }

    // Getters
    /*
     * Returns the puzzles that have been solved
     */
    public HashMap<String, Puzzle> getPuzzlesSolved() {
        return this.puzzlesSolved;
    }
    /*
     * Returns the clues used
     */
    public int getCluesUsed() {
        return this.cluesUsed;
    }
    /*
     * Returns the achievements
     */
    public ArrayList<Achievement> getAchievements() {
        return this.achievements;
    }
    /*
     * Returns the time it took to complete the room
     */
    public int getCompletionTime() {
        return this.completionTime;
    }
    /*
     * Returns the number of the current puzzle
     */
    public int getCurrentPuzzle(){
        return this.currentPuzzle;
    }
    /*
     * Sets the current puzzle
     * @param puzzle sets the puzzle to the number of puzzle
     */
    public void setCurrentPuzzle(int puzzle){
        this.currentPuzzle = puzzle;
    }
    /*
     * Returns the progress in a String
     */
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
