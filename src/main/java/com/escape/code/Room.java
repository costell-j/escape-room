package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
/**
 * This class handles the Room and its methods
 * @author Costell Johnson
 */
public class Room {
    private UUID id;
    private String name;
    private Map map;
    private HashMap<String, Progress> progressList;
    private Progress progress;
    private Leaderboard leaderboard;
    private int timer;
    private ArrayList<Puzzle> puzzles;
    private int difficulty;

    public Room(UUID id, String name, Map map, HashMap<String, Progress> progressList, Progress progress, Leaderboard leaderboard, ArrayList<Puzzle> puzzles, int timer, int difficulty) {
        this.id = id;
        this.name = name;
        this.map = map;
        this.progressList = progressList;
        this.progress = progress;
        this.leaderboard = leaderboard;
        this.puzzles = puzzles;
        this.timer = timer;
        this.difficulty = difficulty;
    }

    // Getters

    public String getName() {
        return this.name;
    }

    public HashMap<String, Progress> getProgressList() {
        return this.progressList;
    }

    public Progress getProgress() {
        return this.progress;
    }

    public Map getMap() {
        return this.map;
    }

    public Leaderboard getLeaderboard() {
        return this.leaderboard;
    }

    public int getTimer() {
        return this.timer;
    }

    public ArrayList<Puzzle> getPuzzles() {
        if(puzzles.isEmpty())
            puzzles.add(new Puzzle(null, null, null, false));
        return this.puzzles;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public UUID getId() {
        return this.id;
    }

    // Setters

    public void setTime(int timer) {
        this.timer = timer;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
    
    // Functionality Methods

    public void playPuzzle() {
        puzzles.get(this.progress.getCurrentPuzzle());
    }

    public void advancePuzzle() {
        if(puzzles.get(this.progress.getCurrentPuzzle()).isSolved()) 
            this.progress.getCurrentPuzzle();
    }
    
    @Override
    public String toString() {
        String desc = "ID: "+this.id+"\nMap: "+this.map.toString()+"\nLeaderboard: "+this.leaderboard.toString();
        desc += "\nTimer: "+this.timer+"\nDifficulty: "+this.difficulty+"\nProgress:\n"+this.progress.toString()+"\nPuzzles:\n";
        for(Puzzle puzzle : this.puzzles) {
            desc += puzzle.toString()+"\n";
        }
        return desc;
    }

}
