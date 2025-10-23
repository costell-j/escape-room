package com.escape.code;

import java.util.ArrayList;

/*
 * Puzzle is the base class for all types of puzzles in the escape room game.
 * It contains common attributes and methods shared by all puzzle types.
 * @author Barbarnas
 */

public abstract class Puzzle<T> {

    protected String description;
    protected String name;
    protected ArrayList<String> hints;
    protected  T solution;
    protected boolean isSolved;
    protected String type;
    protected int shift;
    protected int hintsUsed = 0;
    protected String puzzle;

    /*
    * Constructor for Puzzle class.
    */
    public Puzzle(String description, String name, ArrayList<String> hints, T solution, boolean isSolved) {
        setDescription(description);
        this.name = name;
        setHints(hints);
        this.solution = solution;
        setSolved(isSolved);   
    }
/*
 * T allows for us to use diffent types for solution
 */
    public T getSolution(){
        return this.solution;
    }
    /*
     *  getter that return the type in constructer in subclass of the puzzle.
     */
    public String getType(){
        return this.type;
    }
/*
 * getter used in the desipher class that returns and int for the shift.
 */
    public int getShift(){
        return this.shift;
    }
/*
 * get all the puzzles and returns a specifc puzzle.
 */
    public String getPuzzles(){
        return this.puzzle;
    }
/*
 * gets the description than reurns it.
 */
    public String getDescription() {
        return this.description;
    }
/*
 * gets the name that returns the name
 */
    public String getName() {
        return this.name;
    }
/*
 * gets and array list that a stirng of hints and returns the hints
 */
     public ArrayList<String> getHints() {
         return this.hints;
     }
/*
 * looks to the puzzle class to define the attempt and type
 */
     public abstract void attempt(T param);

/*
 * looks at the how many hints can be used and hits used, then usses them unitl max hint amount reached.
 * And returns no more hints left.
 */
    public String getextHint(){
        if(hintsUsed < hints.size()){
            return hints.get(hintsUsed++);
        }
        else{
            return "none left";
        }
    }
/*
 * resets the hints to 0, when restarted. 
 */
    public void resetHints(){

        hintsUsed = 0;
    }

    /*
    * Marks the puzzle as solved.
    */

    public void solvePuzzle(){
    this.isSolved = true;
    }
/*
 * setter that saves the string into the field puzzle, with a null safe default.
 * if this argumenet is not null return the puzzle
 * if this argument is null return no puzzle found.
 */
    public void setPuzzle(String puzzle){
        this.puzzle = puzzle != null ? puzzle : "No puzzle found";
    }
/*
 * setter that save the string into the field descirpiton, with a null safe default.
 * if null return no description available 
 * if not null return the desription
 */
    public void setDescription(String description) {
        this.description = description != null ? description : "No description available.";
    }

/*
 * setter that saves the array list of hints into the field hints with null safe default 
 * if null return a new Array list of hins 
 * if not null return the hints 
 */
    public void setHints(ArrayList<String> hints) {
        this.hints = hints != null ? hints : new ArrayList<>();
    }

/**
 * Sets whether this puzzle has been solved.
 *
 * @param isSolved {@code true} if the puzzle is solved; 
 *                 {@code false} if it remains unsolved.
 */
    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }


     public boolean isSolved() {
         return this.isSolved;
     }
/*
 * Returns a string representation of the puzzle, including description, solution, solved status, and hints.
 */
    @Override
    public String toString() {
        String puzzle = "Puzzle: "+this.name+"\nDescription: "+this.description+"\nSolution: "+this.solution+"\nSolved: "+this.isSolved+"\nHints:\n";
        for(int i=0; i<this.hints.size(); i++) {
            puzzle += this.hints.get(i)+"\n";
        }
        return puzzle;
    }
}
