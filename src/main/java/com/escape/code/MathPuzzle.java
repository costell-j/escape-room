package com.escape.code;

import java.util.ArrayList;
/*
 * MathPuzzle is a type of Puzzle that involves solving mathematical problems or equations.
 */
public class MathPuzzle extends Puzzle {

    public MathPuzzle(String description, ArrayList<String> hints,
                      String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }

    
    public boolean attempt(String answer) {
        if (answer == null) return false;
        boolean correct = answer.trim().equalsIgnoreCase(getSolution().trim());
        if (correct) {
            
            this.isSolved = true;
        }
        return correct;
    }
}
