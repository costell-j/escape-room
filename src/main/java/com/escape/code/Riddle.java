package com.escape.code;

import java.util.ArrayList;

public class Riddle extends Puzzle {

    public Riddle(String description, ArrayList<String> hints,
                  String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }

    /** Stub: exact-match; you might want to allow close variants or synonyms. */
    public boolean attempt(String answer) {
        if (answer == null) return false;
        boolean correct = answer.trim().equalsIgnoreCase(getSolution().trim());
        if (correct) this.isSolved = true;
        return correct;
    }
}
