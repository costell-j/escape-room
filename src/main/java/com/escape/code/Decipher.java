package com.escape.code;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Decipher 
 * @author Barbarnas
 */

public class Decipher extends Puzzle<String> {
/**
 * Constructs a new {@code Decipher} puzzle object.
 *
 * @param description a short text describing the puzzle or encoded message
 * @param name the name or title of the puzzle
 * @param hints a list of hints available to help solve the puzzle
 * @param solution the correct decrypted answer or phrase
 * @param isSolved whether the puzzle has already been solved
 * @param locked indicates if the puzzle is locked and requires an item to unlock
 * @param item the {@link Item} rewarded or revealed upon solving the puzzle
 * @param givenItem the {@link Item} required to unlock or interact with this puzzle
 * @param shift the integer shift value used for the cipher decryption (e.g., Caesar cipher shift)
 */

    public Decipher(String description, String name, ArrayList<String> hints, String solution, boolean isSolved, boolean locked, Item item, Item givenItem, int shift) {
        super(description, name, hints, solution, isSolved, locked, item, givenItem);
        this.description = description;
        super.type = "Decipher";
        this.shift = shift;
    }
/**
 * Attempts to solve this puzzle using the provided {@code answer}.
 * 
 * The comparison is performed after trimming leading/trailing whitespace
 * and converting both the user input and the stored solution to lowercase,
 * making the check case insensitive and whitespace- nsensitive. If the
 * normalized values are equal, this method marks the puzzle as solved by
 * invoking {@link #solvePuzzle()} on the superclass.
 *
 * @param answer the player's proposed solution; must be non {@code null}
 *
 * @throws NullPointerException if {@code answer} is {@code null}
 * This method performs an exact normalized string match; it does not
 *           accept partial, approximate, or fuzzy matches.
 * @see #solvePuzzle()
 */
    @Override
    public void attempt(String answer) {
        String formattedAnswer = answer.trim().toLowerCase();
        String formattedSolution = this.solution.trim().toLowerCase();

        if(formattedAnswer.equals(formattedSolution)) {
            super.solvePuzzle();
        }
    }
}