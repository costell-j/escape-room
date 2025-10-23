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
    private ArrayList<Slide> story;
    private int difficulty;
    private double score;

    /**
     * Constructor for the Room object
     * @param id Room's UUID
     * @param name Name of the room
     * @param map Room's map
     * @param progressList Room's progress list
     * @param progress Room's progress object
     * @param leaderboard Room's leaderboard
     * @param puzzles Room's array of puzzles
     * @param story Room's' array of stories
     * @param timer Room's timer
     * @param difficulty Room's diffficulty
     */
    public Room(UUID id, String name, Map map, HashMap<String, Progress> progressList, Progress progress, Leaderboard leaderboard, ArrayList<Puzzle> puzzles, ArrayList<Slide> story, int timer, int difficulty) {
        this.id = id;
        this.name = name;
        this.map = map;
        this.progressList = progressList;
        this.progress = progress;
        this.leaderboard = leaderboard;
        this.puzzles = puzzles;
        this.story = story;
        this.timer = timer;
        this.difficulty = difficulty;
        this.score = 1000;
    }

    // Getters

    /**
     * Getter for the room's name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    public ArrayList<Slide> getStory() {
        return this.story;
    }
    
    /**
     * Getter for the room's progress list
     * @return progress list
     */
    public HashMap<String, Progress> getProgressList() {
        return this.progressList;
    }

    /**
     * Getter for room's progress
     * @return progress
     */
    public Progress getProgress() {
        return this.progress;
    }

    /**
     * Gettter for room's map
     * @return map
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Getter for room's leaderboard
     * @return leaderboard
     */
    public Leaderboard getLeaderboard() {
        return this.leaderboard;
    }

    /**
     * Getter for the timer
     * @return timer
     */
    public int getTimer() {
        return this.timer;
    }

    /**
     * Getter for room's puzzles
     * Throws an exception when empty
     * @return puzzles
     */
    public ArrayList<Puzzle> getPuzzles() {
        if(puzzles == null)
            this.puzzles = new ArrayList<Puzzle>();
        if(puzzles.isEmpty())
            throw new IllegalArgumentException("Empty ArrayList");
        return this.puzzles;
    }

    /**
     * Getter for room's difficulty
     * @return difficulty
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * Getter for the room's UUID
     * @return id
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the final score based off the number of clues used and time spent
     */
    public double getFinalScore() {
        switch(difficulty) {
            case 1: 
                this.score -= ((progress.getCluesUsed()*3) + (this.timer - progress.getCompletionTime()));
                break;
            case 2: 
                this.score -= ((progress.getCluesUsed()*2) + (this.timer - progress.getCompletionTime()));
                break;
            case 3:
                this.score -= ((progress.getCluesUsed()) + (this.timer - progress.getCompletionTime()));
                break;
            default:
                this.score = 0;
        }
        if(this.score < 0) {this.score = 0;}
        return this.score;
    }
    // Setters

    /**
     * Setter for the room's timer
     * @param timer the room's time
     */
    public void setTime(int timer) {
        this.timer = timer;
    }

    /**
     * Setter for the room's difficulty
     * Restricts the difficulty to 1-3, defaulted at 1
     * @param difficulty the room's difficulty
     */
    public void setDifficulty(int difficulty) {
        if(difficulty > 0 && difficulty < 4)
            this.difficulty = difficulty;
        else
            this.difficulty = 1;
    }

    /**
     * Setter for the room's leaderboard
     * @param leaderboard the room's leaderboard
     */
    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    /**
     * Setter for Progress based on username
     * If there is no Progress assigned to that username, one is made
     * @param username the user's username
     */
    public void setProgress(String username) {
        for(String s : progressList.keySet()) {
            if(s.equals(username)) {
                this.progress = this.progressList.get(s);
            }
            else {
                Progress progress = new Progress();
                setProgress(progress);
            }
        }
    }

    /**
     * Base setter the room's progress
     * @param progress the room's progress
     */
    public void setProgress(Progress progress) {
        this.progress = progress;
    }
    
    // Functionality Methods

    /**
     * Allows the user to play the current puzzle
     */
    public void playPuzzle() {
        puzzles.get(this.progress.getCurrentPuzzle());
    }

    /**
     * Advances the user onto the next puzzle
     */
    public void advancePuzzle() {
        this.progress.setCurrentPuzzle(this.progress.getCurrentPuzzle()+1);
    }
    
    /**
     * toString for the Room
     */
    @Override
    public String toString() {
        String desc = "ID: "+this.id+"\nMap: "+this.map.toString()+"\nLeaderboard: "+this.leaderboard.toString();
        desc += "\nTimer: "+this.timer+"\nDifficulty: "+this.difficulty+"\nProgress:\n"+this.progress.toString()+"\nPuzzles:\n";
        for(Puzzle puzzle : this.puzzles) {
            desc += puzzle.toString()+"\n";
        }
        return desc;
    }

    /**
     * Method to adjust timer based on difficulty
     * @param difficulty room's difficulty
     */
    public void timeChange(int difficulty) {
        int time = switch(difficulty) {
            case 1 -> 1200;
            case 2 -> 900;
            case 3 -> 600;
            default -> 1200;
        };
        setTime(time);
    }

    /**
     * Method that formats the timer into a min:secs
     * @return the formatted timer
     */
    public String formatTimer() {
        String formattedTimer;
        int timer = getTimer();
        int minutes = timer/60;
        int seconds = timer%60;
        String secondsFormatted;
        if(seconds < 10) {
            secondsFormatted = "0"+seconds;
        }
        else {
            secondsFormatted = ""+seconds+"";
        }

        formattedTimer = minutes+":"+secondsFormatted;

        return formattedTimer;
    }

}
