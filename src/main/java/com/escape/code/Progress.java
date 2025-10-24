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
    private ArrayList<Item> items;
    private HashMap<String, String> hintsUsed;


    public Progress() {
        this.puzzlesSolved = new HashMap<>();
        this.cluesUsed = 0;
        this.completionTime = 0;
        this.currentPuzzle = 0;
        this.achievements = new ArrayList<>();
        this.items = new ArrayList<>();
        this.hintsUsed = new HashMap<>();
    }
    /*
     * Creates a new instance of the progress
     * @param puzzles is the puzzles
     * @param cluesUsed is the amount of clues the user has used
     * @param completionTime is the amount of time the puzzles took to complete
     * @param achievements is the achievements the user has recieved
     */
    public Progress(HashMap<String, Puzzle> puzzles, int cluesUsed, int completionTime, int currentPuzzle, ArrayList<Achievement> achievements, ArrayList<Item> items, HashMap<String, String> hintsUsed){
        this.puzzlesSolved = puzzles;
        this.cluesUsed = cluesUsed;
        this.completionTime = completionTime;
        this.currentPuzzle = currentPuzzle;
        this.achievements = achievements;
        this.items = items;
        this.hintsUsed = hintsUsed;
    }

    // Getters
    /*
     * Returns the puzzles that have been solved
     */
    public HashMap<String, Puzzle> getPuzzlesSolved() {
        return this.puzzlesSolved;
    }

    public HashMap<String, String> getHintsUsed() {
        return this.hintsUsed;
    }

    public ArrayList<Item> getItems() {
        return this.items;
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

    public void addItem(Item i) {
        if(i != null) {
            this.items.add(i);
        }
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
