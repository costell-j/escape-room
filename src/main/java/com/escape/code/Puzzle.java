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
    protected boolean isSolved;
    protected T solution;
    protected String type;
    protected int shift;

    /*
    * Constructor for Puzzle class.
    */
    public Puzzle(String description, String name, T solution, ArrayList<String> hints, boolean isSolved) {
        setDescription(description);
        this.name = name;
        this.solution = solution;
        setHints(hints);
        setSolved(isSolved);   
    }
    /*
    * Marks the puzzle as solved.
    */
    public void solvePuzzle(){
        this.isSolved = true;
    }

    public T getSolution() {
        return this.solution;
    }
    public String getType() {
        return this.type;
    }

    public int getShift() {
        return this.shift;
    }

    public abstract void attempt(T param);
    
    public void setDescription(String description) {
        this.description = description != null ? description : "No description available.";
    }


    public void setHints(ArrayList<String> hints) {
        this.hints = hints != null ? hints : new ArrayList<>();
    }


    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
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

    public boolean isSolved() {
        return this.isSolved;
    }

    /*
    *  Returns a string representation of the puzzle, including description, solution, solved status, and hints.
    */
    @Override
    public String toString() {
        String puzzle = "Description: "+this.description+"\nSolved: "+this.isSolved+"\nHints:\n";
        for(int i=0; i<this.hints.size(); i++) {
            puzzle += this.hints.get(i)+"\n";
        }
        return puzzle;
    }
}
