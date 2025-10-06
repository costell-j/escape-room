package com.escape.code;

import java.util.ArrayList;
/*
 * Decipher is a type of Puzzle that involves decoding messages or ciphers.
 */
public class Decipher extends Puzzle {

    public Decipher(String description, ArrayList<String> hints,
                    String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);
    }
/*
 * Attempts to solve the Decipher puzzle with the provided answer.
 */
    public boolean attempt(String answer) {
        if (answer == null) return false;
        boolean correct = answer.trim().equalsIgnoreCase(getSolution().trim());
        if (correct) this.isSolved = true;
        return correct;
    }
}
