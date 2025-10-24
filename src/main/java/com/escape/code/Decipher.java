package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Decipher 
 * @author Barbarnas
 */

public class Decipher extends Puzzle<String> {


    public Decipher(String description, String name, ArrayList<String> hints, String solution, boolean isSolved, boolean locked, Item item, Item givenItem, int shift) {
        super(description, name, hints, solution, isSolved, locked, item, givenItem);
        this.description = description;
        super.type = "Decipher";
        this.shift = shift;
    }

    @Override
    public void attempt(String answer) {
        String formattedAnswer = answer.trim().toLowerCase();
        String formattedSolution = this.solution.trim().toLowerCase();

        if(formattedAnswer.equals(formattedSolution)) {
            super.solvePuzzle();
        }
    }
}