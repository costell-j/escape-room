package com.escape.code;

import java.util.ArrayList;
/*
 * Riddle is a type of Puzzle that involves answering riddles or word puzzles.
 * @author Barbarnas
 */
public class Riddle extends Puzzle {

    public Riddle(String description, ArrayList<String> hints,
                  String solution, boolean isSolved) {
        super(description, hints, solution, isSolved);

    }
/*
* Checks if the answer roughly matches (ignores case, spaces, and articles).
*/

    public boolean attempt(String answer) {
        if (answer == null) return false;

        String cleanedAnswer = answer.trim().toLowerCase().replaceAll("\\b(a|an|the)\\b", "").replaceAll("\\s+", "");
        String cleanedSolution = getSolution().trim().toLowerCase().replaceAll("\\b(a|an|the)\\b", "").replaceAll("\\s+", "");

        if (cleanedAnswer.equals(cleanedSolution)) {
            this.isSolved = true;
            return true;
        }

        return false;
    }
}