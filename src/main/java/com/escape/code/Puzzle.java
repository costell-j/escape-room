package com.escape.code;

import java.util.ArrayList;

/*
 * Puzzle is the base class for all types of puzzles in the escape room game.
 * It contains common attributes and methods shared by all puzzle types.
 * @author Barbarnas
 */

public class Puzzle {

    protected String description;
    protected String name;
    protected ArrayList<String> hints;
    protected String solution;
    protected boolean isSolved;
    protected String puzzle;
    protected int hintsUsed = 0;

    /*
    * Constructor for Puzzle class.
    */
    public Puzzle(String description, String name, ArrayList<String> hints, String solution, boolean isSolved) {
        setDescription(description);
        this.name = name;
        setHints(hints);
        setSolution(solution);
        setSolved(isSolved);   
    }
    public boolean attempt(String answer){

        return false;
    }
    
    public String getextHint(){
        if(hintsUsed < hints.size()){
            return hints.get(hintsUsed++);
        }
        else{
            return "none left";
        }
    }

    public void resetHints(){

        hintsUsed = 0;
    }
    /*
    * Marks the puzzle as solved.
    */
    public void solvePuzzle(){
    this.isSolved = true;
    }

    public void setPuzzle(String puzzle){
        this.puzzle = puzzle != null ? puzzle : "No puzzle found";
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
     
    public String getPuzzles(){
        return this.puzzle;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

     public ArrayList<String> getHints() {
         return this.hints;
     }

     public String getSolution() {
         return this.solution;
     }

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
