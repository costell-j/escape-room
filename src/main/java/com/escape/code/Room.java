package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Room {
    private UUID id;
    private Map map;
    private HashMap<String, Progress> progressList;
    private Progress progress;
    private Leaderboard leaderboard;
    private int timer;
    private ArrayList<Puzzle> puzzles;
    private int difficulty;
    private int currentPuzzle;

    public Room(UUID id, Map map, HashMap<String, Progress> progressList, Progress progress, Leaderboard leaderboard, ArrayList<Puzzle> puzzles, int timer, int difficulty) {
        this.id = id;
        this.map = map;
        this.progressList = progressList;
        this.progress = progress;
        this.leaderboard = leaderboard;
        this.puzzles = puzzles;
        this.timer = timer;
        this.difficulty = difficulty;
        this.currentPuzzle = 0;
    }

    public HashMap<String, Progress> getProgressList() {
        return this.progressList;
    }

    public Progress getProgress() {
        return this.progress;
    }

    public void setTime(int timer) {
        this.timer = timer;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void modifyTime(int timer) {
        this.timer = timer--;
    }
    public void playPuzzle() {
        puzzles.get(currentPuzzle);
    }
    public void advancePuzzle() {
        if(puzzles.get(currentPuzzle).isSolved) 
            currentPuzzle++;
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
        return this.puzzles;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public int getCurrentPuzzle() {
        return this.currentPuzzle;
    }

    public UUID getId() {
        return this.id;
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

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
}
