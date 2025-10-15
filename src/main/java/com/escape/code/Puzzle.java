package com.escape.code;

import java.util.ArrayList;

/*
 * Puzzle is the base class for all types of puzzles in the escape room game.
 * It contains common attributes and methods shared by all puzzle types.
 * @author Barbarnas
 */

public class Puzzle {

    protected String description;
    protected ArrayList<String> hints;
    protected String solution;
    protected boolean isSolved;

/*
 * Constructor for Puzzle class.
 */
    public Puzzle(String description, ArrayList<String> hints, String solution, boolean isSolved) {
        this.description = description != null ? description : "No description available.";
        this.hints = hints != null ? hints : new ArrayList<>();
        this.solution = solution != null ? solution : "";
        this.isSolved = isSolved;
    }

    
/*
 * Marks the puzzle as solved.
 */
    public void solvePuzzle(){
        this.isSolved = true;
    }
/*
 * Gets the description of the puzzle.
 */
    public String getDescription() {
        return this.description;
    }
/*
 * Gets the list of hints for the puzzle.
 */
    public ArrayList<String> getHints() {
        return this.hints;
    }

/*
 * Gets the solution of the puzzle.
 */
    public String getSolution() {
        return this.solution;
    }
/*
 * Checks if the puzzle is solved.
 */
    public boolean isSolved() {
        return this.isSolved;
    }
/*
 * Returns a string representation of the puzzle, including description, solution, solved status, and hints.
 */
    @Override
    public String toString() {
        String puzzle = "Description: "+this.description+"\nSolution: "+this.solution+"\nSolved: "+this.isSolved+"\nHints:\n";
        for(int i=0; i<this.hints.size(); i++) {
            puzzle += this.hints.get(i)+"\n";
        }
        return puzzle;
    }
}
