package com.escape.code;

import java.util.ArrayList;
/*
 * Logic is a type of Puzzle that involves reasoning and problem-solving skills.
 * @author Barbarnas
 */
public class Logic extends Puzzle {

    public Logic(String description, ArrayList<String> hints,
                 String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }
/*
 * Attempts to solve the Logic puzzle with the provided answer.
 */
 public boolean attempt(String answer) {
        if (answer == null) return false;

        String normalized = answer.trim().toLowerCase()
                .replace("&&", "and")
                .replace("||", "or")
                .replace("t", "true")
                .replace("f", "false");

        String expected = getSolution().trim().toLowerCase()
                .replace("&&", "and")
                .replace("||", "or")
                .replace("t", "true")
                .replace("f", "false");

        if (normalized.equals(expected)) {
            this.isSolved = true;
            return true;
        }

        return false;
    }
}

