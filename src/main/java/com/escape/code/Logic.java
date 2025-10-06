package com.escape.code;

import java.util.ArrayList;

public class Logic extends Puzzle {

    public Logic(String description, ArrayList<String> hints,
                 String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }

  
    public boolean attempt(String answer) {
        if (answer == null) return false;
        boolean correct = answer.trim().equalsIgnoreCase(getSolution().trim());
        if (correct) this.isSolved = true;
        return correct;
    }
}

