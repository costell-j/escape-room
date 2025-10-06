package com.escape.code;

import java.util.ArrayList;
/*
 * Riddle is a type of Puzzle that involves answering riddles or word puzzles.
 */
public class Riddle extends Puzzle {

    public Riddle(String description, ArrayList<String> hints,
                  String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }

   /*
    * Attempts to solve the Riddle puzzle with the provided answer.
    */
    public boolean attempt(String answer) {
        if (answer == null) return false;
        boolean correct = answer.trim().equalsIgnoreCase(getSolution().trim());
        if (correct) this.isSolved = true;
        return correct;
    }
}
