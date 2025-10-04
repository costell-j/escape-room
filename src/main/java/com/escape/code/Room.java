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

    public Room(UUID id, Map map, Leaderboard leaderboard, ArrayList<Puzzle> puzzles, int timer, int difficulty) {
        this.id = id;
        this.map = map;
        this.leaderboard = leaderboard;
        this.puzzles = puzzles;
        this.timer = timer;
        this.difficulty = difficulty;
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
        puzzles.get(0);
    }
    public void advancePuzzle() {
        if(puzzles.get(0).isSolved) {
            
        }

    }
}
