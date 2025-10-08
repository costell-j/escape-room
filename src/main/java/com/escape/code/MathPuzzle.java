package com.escape.code;

import java.util.ArrayList;
/*
 * MathPuzzle is a type of Puzzle that involves solving mathematical problems or equations.
 * @author Barbarnas
 */
public class MathPuzzle extends Puzzle {
/*
 * Constructor for MathPuzzle class.
 */
    public MathPuzzle(String description, ArrayList<String> hints,
                      String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }

/*
 * Attempts to solve the MathPuzzle with the provided answer.
 */
    public boolean attempt(String answer) {
        if (answer == null) return false;
        boolean correct = answer.trim().equalsIgnoreCase(getSolution().trim());
        if (correct) {
            
            this.isSolved = true;
        }
        return correct;
    }
}
