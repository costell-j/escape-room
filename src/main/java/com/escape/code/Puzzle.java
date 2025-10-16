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
       
        setDescription(description);
        setHints(hints);
        setSolution(solution);
        setSolved(isSolved);   
    }
/*
 * Marks the puzzle as solved.
 */
    public void solvePuzzle(){
    this.isSolved = true;
    }
    
    public void setDescription(String description) {
        this.description = description != null ? description : "No description available.";
    }


    public void setHints(ArrayList<String> hints) {
        this.hints = hints != null ? hints : new ArrayList<>();
    }


    public void setSolution(String solution) {
        this.solution = solution != null ? solution : "";
    }


    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public String getDescription() {
        return description;
    }

     public ArrayList<String> getHints() {
         return hints;
     }

     public String getSolution() {
         return solution;
     }

     public boolean isSolved() {
         return isSolved;
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
