package com.escape.code;

import java.util.ArrayList;

public class Puzzle {

    protected String description;
    protected ArrayList<String> hints;
    protected String solution;
    protected boolean isSolved;

    public Puzzle(String description, ArrayList<String> hints, String solution, boolean isSolved) {
        this.description = description;
        this.hints = hints;
        this.solution = solution;
        this.isSolved = isSolved;
    }
    
    public void solvePuzzle(){
        if(isSolved){
            isSolved = true;
        }
    }

    @Override
    public String toString() {
        String puzzle = "Description: "+this.description+"\nSolution: "+this.solution+"\nSolved: "+this.isSolved+"\nHints:\n";
        for(int i=0; i<this.hints.size(); i++) {
            puzzle += this.hints.get(i)+"\n";
        }
        return puzzle;
    }
}
