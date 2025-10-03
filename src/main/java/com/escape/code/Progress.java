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
}
