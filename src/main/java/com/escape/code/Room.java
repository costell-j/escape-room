package com.escape.code;

import java.util.ArrayList;
import java.util.UUID;

public class Room {
    private UUID id;
    private Map map;
    private Leaderboard leaderboard;
    private int timer;
    private ArrayList<Puzzle> puzzles;
    private int difficulty;
    private int currentPuzzle;

    public Room(UUID id, Map map, Leaderboard leaderboard, ArrayList<Puzzle> puzzles, int timer, int difficulty) {
        this.id = id;
        this.map = map;
        this.leaderboard = leaderboard;
        this.puzzles = puzzles;
        this.timer = timer;
        this.difficulty = difficulty;
        this.currentPuzzle = 0;
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
        desc += "\nTimer: "+this.timer+"\nDifficulty: "+this.difficulty+"\nPuzzles:\n";
        for(Puzzle puzzle : this.puzzles) {
            desc += puzzle.toString()+"\n";
        }
        return desc;
    }
}
